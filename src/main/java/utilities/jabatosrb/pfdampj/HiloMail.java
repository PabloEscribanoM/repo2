package jabatosrb.pfdampj;

public class HiloMail extends Thread{

    private String destinatario, asutno, cuerpo;

    public HiloMail(String destinatario, String asutno, String cuerpo){
        this.destinatario = destinatario;
        this.asutno = asutno;
        this.cuerpo = cuerpo;
    }

    @Override
    public void run() {
        Mail.enviarEmail(destinatario, asutno, cuerpo);
    }
}
