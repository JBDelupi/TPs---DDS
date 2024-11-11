package Service.TareaDiferida;

public interface AdapterChromeTask {
    public void ejecutarTareaPrograma(int tiempo, Object objeto, String metodo);
    public void pausarTarea();
    public boolean estaActivado();
}
