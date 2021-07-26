package clean.code.design_patterns.requirements;

import java.util.Arrays;

public class Waiter implements Observer{


    private String name;
    public Waiter(String name){
        this.name = name;
    }

    public void listenTo(Client client){
        client.register(this);
    }
    @Override
    public void update(String message) {
        System.out.println("Client has ordered: "+ message );
    }
}
