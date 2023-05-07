package client.main;


import client.entities.MasterThief;
import client.entities.OrdinaryThief;
import client.stubs.*;
import client.stubs.GeneralReposStub;
import genclass.GenericIO;

import static client.main.ConnectionData.*;
import static utils.Parameters.*;

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
        GeneralReposStub repos;

        /* problem initialization */

        assaultPartyStub[0] = new AssaultPartyStub(ASSAULT_PARTY_0_MACHINE, ASSAULT_PARTY_0_PORT);
        assaultPartyStub[1] = new AssaultPartyStub(ASSAULT_PARTY_1_MACHINE, ASSAULT_PARTY_1_PORT);
        collectionSiteStub = new CollectionSiteStub(COLLECTION_SITE_MACHINE, COLLECTION_SITE_PORT);
        concentrationSiteStub = new ConcentrationSiteStub(CONCENTRATION_SITE_MACHINE, CONCENTRATION_SITE_PORT);
        museumStub = new MuseumStub(MUSEUM_MACHINE, MUSEUM_PORT);
        repos = new GeneralReposStub(GENERAL_REPOS_MACHINE, GENERAL_REPOS_PORT);

        /* init master thieves */
        for (int i = 0; i < N_THIEVES_MASTER; i++)
            master[i] = new MasterThief("Master_"+i, i, museumStub, concentrationSiteStub, collectionSiteStub, assaultPartyStub, repos);

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
        }
        GenericIO.writelnString ();
        assaultPartyStub[0].shutDown();
        assaultPartyStub[1].shutDown();
        collectionSiteStub.shutDown();
        concentrationSiteStub.shutDown();
        museumStub.shutDown();
        repos.shutDown();
    }
}
