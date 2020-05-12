package organ;

import mediator.BodyMediator;

public abstract class Organ {

    private BodyMediator mediator;

    public Organ(BodyMediator mediator){
        this.mediator = mediator;
    }

    public BodyMediator getMediator(){
        return mediator;
    }

}
