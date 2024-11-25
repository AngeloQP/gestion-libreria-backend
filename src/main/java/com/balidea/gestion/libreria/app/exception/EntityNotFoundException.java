package com.balidea.gestion.libreria.app.exception;

public class EntityNotFoundException extends RuntimeException {
    private final String entityName;
    private final Object entityId;

    public EntityNotFoundException(String entityName, Object entityId) {
        super(String.format("%s no encontrado con ID: %s", entityName, entityId));
        this.entityName = entityName;
        this.entityId = entityId;
    }

    public String getDetails() {
        return String.format("%s con ID %s no ha sido encontrado.", entityName, entityId);
    }
}
