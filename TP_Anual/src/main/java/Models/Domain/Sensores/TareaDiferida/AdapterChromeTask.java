package Models.Domain.Sensores.TareaDiferida;

public interface AdapterChromeTask {
    public void ejecutarTareaPrograma(int tiempo, Object objeto, String metodo);
    public void pausarTarea();
}
