package billeterie;

public class FormulaireException extends RuntimeException{ 
	
	public FormulaireException(){
		    System.out.println("Veuillez remplir compl�tement le formulaire avant de valider vos saisies !");
	}  
}
