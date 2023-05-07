package client.entities;

import client.stubs.*;
import genclass.GenericIO;
import server.sharedRegions.*;

import static utils.Parameters.*;
import static utils.Utils.*;

/**
 * Thread that represents the Ordinary Thief
 */

public class OrdinaryThief extends Thief {
    private int partyID;
    private int roomID;
    /**
     * Displacement of the thief
     */
    private final int displacement;

    private GeneralReposStub repos;

    /**
     * Ordinary Thief Constructor
     * @param threadName Thread name
     * @param thiefID Thief ID
     * @param museum Museum
     * @param concentrationSite Concentration Site
     * @param collectionSite Collection Site
     * @param assaultParties Assault Parties
     */

    public OrdinaryThief(String threadName, int thiefID, MuseumStub museum, ConcentrationSiteStub concentrationSite, CollectionSiteStub collectionSite, AssaultPartyStub[] assaultParties, GeneralReposStub repos){
        super(threadName, thiefID, museum, concentrationSite, collectionSite, assaultParties,repos);
        thiefState = OrdinaryThiefStates.CONCENTRATION_SITE;
        displacement = random(MIN_DISPLACEMENT, MAX_DISPLACEMENT);
        this.repos = repos;
        partyID = roomID = -1;
        repos.setOrdinaryThiefDisplacement(thiefID, displacement);
    }

    @Override
    public String toString() {
        return super.toString() + " [Party: " + partyID + ", Room: " + roomID + "]";
    }

    /**
     * Life cycle of the Ordinary Thief
     */
    @Override
    public void run() {
        while (concentrationSite.amINeeded()) {
            int[] assaultData = concentrationSite.prepareExcursion();
            if (assaultData != null) {
                partyID = assaultData[0];
                roomID = assaultData[1];
                AssaultPartyStub party = assaultParties[partyID];

                party.crawlIn(museum.getRoomDistance(roomID), displacement);
                boolean hasCanvas = museum.rollACanvas(roomID);
                if (hasCanvas)
                    museum.setRoomPaintings(roomID, museum.getRoomPaintings(roomID) - 1);
                GenericIO.writelnString("Canvas: " + museum.getRoomPaintings(roomID));
                party.reverseDirection();
                party.crawlOut(museum.getRoomDistance(roomID), displacement);

                collectionSite.handACanvas(partyID, roomID, hasCanvas);
                repos.setOrdinaryThiefSituation(getThiefID(), false);
            } else break;
        }
    }
}
