package server.sharedRegions;

import server.entities.*;
import utils.*;
import server.main.*;
import server.sharedRegions.*;


public class GeneralReposInterface {
    /**
     * Reference to the general repository.
     */

    private final GeneralRepos repos;

    /**
     * Instantiation of an interface to the general repository.
     *
     * @param repos reference to the general repository
     */

    public GeneralReposInterface(GeneralRepos repos) {
        this.repos = repos;
    }

    /**
     * Processing of the incoming messages.
     * <p>
     * Validation, execution of the corresponding method and generation of the outgoing message.
     *
     * @param inMessage service request
     * @return service reply
     * @throws MessageException if the incoming message is not valid
     */

    public Message processAndReply(Message inMessage) throws MessageException {
        Message outMessage = null;                           // mensagem de resposta

        /* validation of the incoming message */
        switch (inMessage.getMsgType()) {
            case MessageType.SETNFIC:
                repos.initSimul(inMessage.getLogFName());
                outMessage = new Message(MessageType.NFICDONE);
                break;
            case MessageType.UPMTSTATE:
                repos.updateMasterThiefState(inMessage.getMasterThiefState());
                outMessage = new Message(MessageType.UPMTSTATEDONE);
                break;
            case MessageType.SETOTDISP:
                repos.setOrdinaryThiefDisplacement(inMessage.getOrdinaryThiefId(), inMessage.getDisplacement());
                outMessage = new Message(MessageType.SETOTDISPDONE);
                break;
            case MessageType.SETOTSIT:
                repos.setOrdinaryThiefSituation(inMessage.getOrdinaryThiefId(), inMessage.getOrdinaryThiefSituation());
                outMessage = new Message(MessageType.SETOTSITDONE);
                break;
            case MessageType.SETOTSTATE:
                repos.setOrdinaryThiefSituation(inMessage.getOrdinaryThiefId(), inMessage.getOrdinaryThiefSituation());
                outMessage = new Message(MessageType.SETOTSTATEDONE);
                break;
            case MessageType.SETOTAPID:
                repos.setOrdinaryThiefAssaultPartyID(inMessage.getOrdinaryThiefId(), inMessage.getAssaultPartyId());
                outMessage = new Message(MessageType.SETOTAPIDDONE);
                break;
            case MessageType.SETOTPOS:
                repos.setOrdinaryThiefPosition(inMessage.getOrdinaryThiefId(), inMessage.getOrdinaryThiefPosition());
                outMessage = new Message(MessageType.SETOTPOSDONE);
                break;
            case MessageType.SETOTCANVAS:
                repos.setOrdinaryThiefCanvas(inMessage.getOrdinaryThiefId(), inMessage.hasCanvas());
                outMessage = new Message(MessageType.SETOTCANVASDONE);
                break;
            case MessageType.SETNPAINTINGS:
                repos.setnPaintings(inMessage.getRoomId(),inMessage.getRoomPaintings());
                outMessage = new Message(MessageType.SETNPAINTINGSDONE);
                break;
            case MessageType.SETNCANVAS:
                repos.setnCanvas(inMessage.getnCanvas());
                outMessage = new Message(MessageType.SETNCANVASDONE);
                break;
            case MessageType.PRINTSUMUP:
                repos.printSumUp();
                outMessage = new Message(MessageType.PRINTSUMUPDONE);
                break;
            case MessageType.SETDISTTOROOM:
                repos.setDistanceToRoom(inMessage.getRoomId(), inMessage.getRoomDistance());
                outMessage = new Message(MessageType.SETDISTTOROOMDONE);
                break;

        }

        return (outMessage);
    }
}



