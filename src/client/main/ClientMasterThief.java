package client.main;

import client.entities.MasterThief;
import client.stubs.AssaultPartyStub;
import client.stubs.CollectionSiteStub;
import client.stubs.ConcentrationSiteStub;
import client.stubs.MuseumStub;
import genclass.GenericIO;

import static utils.Parameters.*;

import java.io.FileNotFoundException;

/**
 *    Client side of the Master Thief.
 *
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 */
 
public class ClientMasterThief
{
    /**
     *  Main method.
     */

    public static void main (String [] args)
    {
        MasterThief[] master = new MasterThief[N_THIEVES_MASTER];
        AssaultPartyStub[] assaultPartyStub = new AssaultPartyStub[N_ASSAULT_PARTIES];
        CollectionSiteStub collectionSiteStub;
        ConcentrationSiteStub concentrationSiteStub;
        MuseumStub museumStub;

        /* problem initialization */

        ConnectionData connData;
        try {
            connData = new ConnectionData("config");
        } catch (FileNotFoundException e) {
            GenericIO.writelnString("Configuration file not found!");
            e.printStackTrace();
            return;
        } catch (Exception e) {
            GenericIO.writelnString("Error reading configuration file!");
            e.printStackTrace();
            return;
        }

        String ASSAULT_PARTY_0_MACHINE = connData.getMachine("ASSAULT_PARTY_A");
        int ASSAULT_PARTY_0_PORT = connData.getPort("ASSAULT_PARTY_A");
        String ASSAULT_PARTY_1_MACHINE = connData.getMachine("ASSAULT_PARTY_B");
        int ASSAULT_PARTY_1_PORT = connData.getPort("ASSAULT_PARTY_B");
        String COLLECTION_SITE_MACHINE = connData.getMachine("COLLECTION_SITE");
        int COLLECTION_SITE_PORT = connData.getPort("COLLECTION_SITE");
        String CONCENTRATION_SITE_MACHINE = connData.getMachine("CONCENTRATION_SITE");
        int CONCENTRATION_SITE_PORT = connData.getPort("CONCENTRATION_SITE");
        String MUSEUM_MACHINE = connData.getMachine("MUSEUM");
        int MUSEUM_PORT = connData.getPort("MUSEUM");

        assaultPartyStub[0] = new AssaultPartyStub(ASSAULT_PARTY_0_MACHINE, ASSAULT_PARTY_0_PORT);
        assaultPartyStub[1] = new AssaultPartyStub(ASSAULT_PARTY_1_MACHINE, ASSAULT_PARTY_1_PORT);
        collectionSiteStub = new CollectionSiteStub(COLLECTION_SITE_MACHINE, COLLECTION_SITE_PORT);
        concentrationSiteStub = new ConcentrationSiteStub(CONCENTRATION_SITE_MACHINE, CONCENTRATION_SITE_PORT);
        museumStub = new MuseumStub(MUSEUM_MACHINE, MUSEUM_PORT);

        /* init master thieves */
        for (int i = 0; i < N_THIEVES_MASTER; i++)
            master[i] = new MasterThief("Master_"+i, i, museumStub, concentrationSiteStub, collectionSiteStub, assaultPartyStub);

        /* start of the simulation */

        for (int i = 0; i < N_THIEVES_MASTER; i++)
            master[i].start ();

        /* waiting for the end of the simulation */

        GenericIO.writelnString ();
        for (int i = 0; i < N_THIEVES_MASTER; i++)
        { while (master[i].isAlive ())
        {
            Thread.yield ();
        }
            try
            { master[i].join ();
            }
            catch (InterruptedException e) {}
            GenericIO.writelnString ("Master_" + (i+1) + " has terminated.");
            assaultPartyStub[0].shutDown();
            assaultPartyStub[1].shutDown();
            collectionSiteStub.shutDown();
            concentrationSiteStub.shutDown();
            museumStub.shutDown();
        }
        GenericIO.writelnString ();
    }
}
