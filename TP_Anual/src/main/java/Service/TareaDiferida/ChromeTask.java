package Service.TareaDiferida;

public class ChromeTask implements AdapterChromeTask{
    private  AdapterScheduledExecutorService adapterChromeTask;

    public ChromeTask(){
        this.adapterChromeTask = new AdapterScheduledExecutorService();

    }

    public boolean estaActivado(){
       return adapterChromeTask.estaActiva();
    }

    public void ejecutarTareaPrograma(int periodo, Object objeto, String metodo){
        adapterChromeTask.ejecutarTareaPrograma(periodo,objeto,metodo);
    }
    public void pausarTarea(){
        adapterChromeTask.pausarTareaProgramada();
    }

}