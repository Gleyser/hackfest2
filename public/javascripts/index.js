$(document).on('ready', function(event) {
	
	createValidations();
	
	$('#principal-temas a').on('click', function() {
		$('#principal-temas a').removeClass('active');
		$(this).addClass('active');
		$($("#temas").find("[data-id='" + $(this).data('id') + "']")).trigger('click');
		mySwiper.swipeTo(1);
	});
	
	$('#temas a').on('click', function() {
		$('#temas a').removeClass('active');
		$(this).addClass('active');

		$('#eventos-principal').empty();

		$.ajax({
			url : '/eventos/tema/' + $(this).data('id'),
			type : 'GET',
			success : function(result) {
				var data = JSON.parse(result);
				var tableTemplate = $('#evento-template');
				
				if (data.length > 0){
					$('#visualizacao-nenhum-evento').hide();
					for (var i = 0; i < data.length; i++) {
						var table = tableTemplate.clone();
	
						table.find('tr').attr('data-info', JSON.stringify(data[i]));
	
						table.find('.titulo').html(data[i].titulo).attr('title', data[i].titulo);
						table.find('.data').html(new Date(data[i].data).toLocaleDateString());
						table.find('.total-participantes').html(data[i].totalDeParticipantes);

						table.find('.btn-participar').on('click', function(){
							var idEvento = $(this).parent().parent().data('info').id;
							$('#form-evento-id').val(idEvento)
						});
						
						table.find('.btn-maisInformacoes').on('click', function(){
							var modal = $("#modalMaisInformacoes");
							var infoEvento = $(this).parent().parent().data('info');
							
							modal.find('.modal-titulo-evento').html(infoEvento.titulo);
							modal.find('.modal-descricao-evento').html(infoEvento.descricao);
							modal.find('.modal-data-evento').val(new Date(infoEvento.data).toLocaleDateString());
							modal.find('.modal-total-evento').val(infoEvento.totalDeParticipantes);
							
							modal.modal('show');
						});
						
						$('#eventos-principal').append(table.find('tr'));
					}
				}else{
					$('#visualizacao-nenhum-evento').show();
				}
			}
		});
	});
	
	$('#form-novo-evento').on('submit', function(e){

		e.preventDefault();
		if($(this).valid()){
			$.ajax({
				url : $(this).attr('action'),
				data: new FormData(this),
				type : 'POST',
				mimeType:"multipart/form-data",
			    contentType: false,
		        cache: false,
		        processData:false,
				success : function(result) {
					$('#temas .active').trigger('click');
					$('#form-novo-evento')[0].reset();
					
					$('.top-right').notify({
						 message : {
						 	text : 'Evento criado com sucesso'
						 },
						 type : "success"
					 }).show(); 
				},
				error: function (){
					 $('.top-right').notify({
						 message : {
						 	text : 'Erro ao criar o evento'
						 },
						 type : "danger"
					 }).show(); 
				}
			});
		}else{
			var tooltips = $('#criacao-temas .tooltip');
			
			$.each(tooltips, function(){
				$(this).css('left',parseInt($('.tooltip').css('left')) + 90 + 'px');
			});
			
		}
	});
	
	$('#form-participar').on('submit', function(e){
		e.preventDefault();
		
		if($(this).valid()){
			$.ajax({
				url : "eventos/" + $('#form-evento-id').val() + "/participar",
				data: new FormData(this),
				type : 'POST',
				mimeType:"multipart/form-data",
			    contentType: false,
		        cache: false,
		        processData:false,
				success : function(result) {
					$('#temas .active').trigger('click');
					
					$('#modalParticipar').modal('hide');
					
					$('.top-right').notify({
						 message : {
						 	text : 'Evento criado com sucesso'
						 },
						 type : "success"
					 }).show(); 
				},
				error: function (){
					 $('.top-right').notify({
						 message : {
						 	text : 'Erro ao criar o evento'
						 },
						 type : "danger"
					 }).show(); 
				}
			});
		}
	});
	
	$('#modalParticipar').on('hidden.bs.modal', function (e) {
		$('#form-participar')[0].reset();
	})

	$('#temas a:first').trigger('click')

});

function createValidations(){
	
	jQuery.validator.addMethod("menorQueAtual", function(value, element, params) {
	    if (!/Invalid|NaN/.test(new Date(value))) {
	    	
	        return new Date(new Date(value).setHours(0,0,0,0)) >= new Date(new Date().setHours(0,0,0,0));
	    }

	    return isNaN(value) && isNaN($(params).val()) 
	        || (Number(value) > Number($(params).val())); 
	},'A data deve ser maior que a atual.');
	
	
	jQuery.validator.addMethod('regex', function(value, element, regexp) {
		var re = new RegExp(regexp);
        return this.optional(element) || re.test(value);
	},'Please check your input.');
	

	$('#form-participar').validate({
		rules:{ 
            nome:{
            	required: true
            },
            email:{
            	required: true,
            	regex: "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
            }
        },
        messages:{
            nome :{
            	required: "Nome é obrigatório."
            },
            
            email:{
            	required: "Email é obrigatório.",
            	regex: "Digite um email válido."
            }
            
        }
	});
	
	$('#form-novo-evento').validate({
        rules:{ 
            titulo:{ 
                required: true,
            	maxlength: 40
            },
            data:{ 
                required: true,
                menorQueAtual: true
            },
            descricao:{
            	required: true,
            	maxlength: 450
            },
            'temas[]':{
            	required: true
            }
        },
        messages:{
        	titulo:{ 
                required: "É necessário um título.",
                maxlength: "O título deve conter no máximo 30 caracteres."
            },
            data:{ 
                required: "A data é obrigatoria."
            },
            descricao:{
            	required: "É necessário uma descrição.",
                maxlength: "A descrição deve conter no máximo 350 caracteres."
            },
            'temas[]':{
            	required: "É necessário escolher pelo menos 1 tema."
            }            
        }
         
    });
}