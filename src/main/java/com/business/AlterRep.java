package com.business;

import net.fortuna.ical4j.model.Content;
import net.fortuna.ical4j.model.Parameter;
import net.fortuna.ical4j.model.ParameterFactory;

public class AlterRep extends Parameter {
    private static  final String ALTREP = "ALTREP";
    private String value;

    public AlterRep(final String value) {
        super(ALTREP, new AlterRep.Factory());
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public final String getValue() {
        return value;
    }

    public static class Factory extends Content.Factory implements ParameterFactory {

        Factory() {
            super(ALTREP);
        }

        public Parameter createParameter(final String value) {
            return new AlterRep(value);
        }
    }
}
