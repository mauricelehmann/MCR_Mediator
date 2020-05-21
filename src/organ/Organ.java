package organ;

import mediator.BodyMediator;

public abstract class Organ {

    protected BodyMediator mediator;

    public Organ(BodyMediator mediator){
        this.mediator = mediator;
    }

    public BodyMediator getMediator(){
        return mediator;
    }

    
}
