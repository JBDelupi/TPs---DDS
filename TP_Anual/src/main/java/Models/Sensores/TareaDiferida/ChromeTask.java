package Models.Sensores.TareaDiferida;

public class ChromeTask implements AdapterChromeTask{
    private  AdapterScheduledExecutorService adapterChromeTask;

    public ChromeTask(int periodo, Object object, String metodo){
        this.adapterChromeTask = new AdapterScheduledExecutorService();
        this.ejecutarTareaPrograma(periodo, object, metodo);
    }

    public void ejecutarTareaPrograma(int periodo, Object objeto, String metodo){
        adapterChromeTask.ejecutarTareaPrograma(periodo,objeto,metodo);
    }
    public void pausarTarea(){
        adapterChromeTask.pausarTareaProgramada();
    }


}
