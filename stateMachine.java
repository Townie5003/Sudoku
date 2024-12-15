

public class stateMachine{
    private String state;


    public stateMachine(String given_state){
        this.state = given_state;   
    }
    public String returnState(){
        return this.state;
    }
    public void setState(String new_state){
        this.state = new_state;
    }
    
}

