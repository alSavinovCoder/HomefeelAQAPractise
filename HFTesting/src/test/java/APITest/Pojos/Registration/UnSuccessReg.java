package APITest.Pojos.Registration;

public class UnSuccessReg extends Register {
    private String error;

    public UnSuccessReg(String error,
                            String email,
                            String password) {
        super(email,password);
        this.error = error;
    }

    public UnSuccessReg() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error){
        this.error = error;

    }

    public String getEmail(){
        return super.getEmail();
    }

    public void setEmail(String email){
        super.setEmail(email);
    }

    public String getPassword(){
        return super.getPassword();
    }

    public void setPassword(String password){
        super.setPassword(password);
    }
}
