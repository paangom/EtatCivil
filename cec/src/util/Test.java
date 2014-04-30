package util;

import java.io.File;
import java.io.IOException;

public class Test { 
public static void main(String[] args) {
File file = new File ("C:/Users/admin/git/cec/cec/parametre/test.txt");
System.out .println ( file.exists ( ) );  // true car memoire.txt existe sur disque
File f = new File("c:/memoire2.txt");
System.out .println( f.exists ( ) );// false car memoire2.txt n’existe pas encore
try {
boolean creation = f.createNewFile ( );// memoire2.txt est créé sur disque
}
catch ( IOException ex) 
{ex.printStackTrace() ;}
System.out .println (f.exists ( ) );
}
}