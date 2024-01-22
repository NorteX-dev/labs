package com.nortexdev;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class DirectoryChecker implements Runnable {
	// Pliki z "pathA" będą kopiowane do "pathB".
	String pathA = "/home/nortex/labs/java/lab9/katalogA";
	String pathB = "/home/nortex/labs/java/lab9/katalogB";

	// Ta metoda będzie wywoływana co 5 sekund.
	@Override
	public void run() {
		System.out.println("Checking...");

		// Odczyt plików z folderu A
		File[] filesInA = new File(pathA).listFiles();
		File[] filesInB = new File(pathB).listFiles();

		if (filesInA.length == 0) {
			System.out.println("No files found in A.");
			return;
		}

		for (File fileA : filesInA) {
			// Pobieram nazwę pliku z folderu A...
			String fileName = fileA.getName();
			// ...i sprawdzam czy istnieje w folderze B.
			File fileB = new File(pathB + "/" + fileName);
			if (!fileB.exists()) {
				// Jeżeli nie istnieje, kopiuję.
				System.out.println("Copying " + fileName + " to " + pathB);
				try {
					Files.copy(fileA.toPath(), fileB.toPath());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		for(File fileB : filesInB) {
			String fileName = fileB.getName();
			File fileA = new File(pathA + "/" + fileName);
			if (!fileA.exists()) {
				System.out.println("Deleting " + fileName + " from " + pathB);
				fileB.delete();
			}
		}
	}
}
