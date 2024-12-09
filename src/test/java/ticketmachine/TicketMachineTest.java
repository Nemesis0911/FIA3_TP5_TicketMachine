package ticketmachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@BeforeEach
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de
	// l'initialisation
	// S1 : le prix affiché correspond à l’initialisation.
	void priceIsCorrectlyInitialized() {
		// Paramètres : valeur attendue, valeur effective, message si erreur
		assertEquals(PRICE, machine.getPrice(), "Initialisation incorrecte du prix");
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	void insertMoneyChangesBalance() throws ExceptionInsertionMontantNegatif {
		// GIVEN : une machine vierge (initialisée dans @BeforeEach)
		// WHEN On insère de l'argent
		machine.insertMoney(10);
		machine.insertMoney(20);
		// THEN La balance est mise à jour, les montants sont correctement additionnés
		assertEquals(10 + 20, machine.getBalance(), "La balance n'est pas correctement mise à jour");
	}

	@Test
	//S3 : on n'imprime pas le ticket si le montant inséré est insuffisant
	void nImprimerPasticketSiMontantInuffisant() throws ExceptionInsertionMontantNegatif {
		machine.insertMoney(PRICE-1);

		assertFalse(machine.printTicket(),"On a pu imprimer le ticket alors qui avait pas assez de sous");

	}

	@Test
	//S4 : on n'imprime pas le ticket si le montant inséré est suffisant
	void imprimerTicketSiSuffisant() throws ExceptionInsertionMontantNegatif {
		machine.insertMoney(PRICE);

		assertTrue(machine.printTicket(),"on n'a pas pu imprimer le ticket alors qu'il y a les sous ");
	}

	@Test
	//S5 : Quand on imprime un ticket la balance est décrémentée du prix du ticket
	void balanceDecrementeDuPrixQuandOnImprime() throws ExceptionInsertionMontantNegatif {
		machine.insertMoney(PRICE);
		int ancienPrix = machine.getBalance();
		machine.printTicket();

		assertEquals(machine.getBalance(),ancienPrix-PRICE,"la balance est pas décrémentée du prix du ticket");
	}

	@Test
	//S6 : le montant collecté est mis à jour quand on imprime le ticket
	void montantCollecteMisAJourQuandOnImprime() throws ExceptionInsertionMontantNegatif {
		int ancientotal = machine.getTotal();
		machine.insertMoney(PRICE);
		machine.printTicket();

		assertEquals(machine.getTotal(),ancientotal+PRICE,"le montant collecté n'est pas mis à jour");
	}

	@Test
	//S7 : refund() rends correctement la monnaie
	void rendsCorrectementLaMonnaie() throws ExceptionInsertionMontantNegatif {
		machine.insertMoney(PRICE+3);
		machine.printTicket();

		assertEquals(machine.refund(),machine.getBalance(),"ne rends pas correctement la monnaie");
	}

	@Test
	//S8 : refund() remet la balance à zéro
	void remetBalanceZero() throws ExceptionInsertionMontantNegatif {
		machine.insertMoney(PRICE+10);
		machine.printTicket();

		assertEquals(machine.refund(),0,"la balance  n'est pas remise à zéro");
	}

	@Test
	//S9 : On ne peut pas inérer un montant négatif
	void pasInsererMontantNegatif(){

		try{
			machine.insertMoney(-1);
			fail("La machine a entrée un montant négatif");
		}
		catch(Exception e){
		}
	}

	@Test
	//S10 : la machine n'imprime pas des coupons négatifs
	void machineNimprimePasCouponsNegatifs() throws Exception{

		try{
			new  TicketMachine(-PRICE);
			fail("la machine est à prix négatif");
		}
		catch(Exception e){

		}
	}

}
