package models;

import java.util.Comparator;

public class EventoComparator implements Comparator<Evento> {
	@Override
    public int compare(Evento e1, Evento e2) {
       return e2.getTotalDeParticipantes().compareTo(e1.getTotalDeParticipantes());
    }
}