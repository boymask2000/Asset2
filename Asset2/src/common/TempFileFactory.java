package common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TempFileFactory {
	private static List<File> lista = new ArrayList<File>();

	public static File getTempFile(String suffix) throws IOException {
		File f = File.createTempFile("tmp", suffix);
		lista.add(f);
		return f;
	}

	public static File createTempFile(String name, String ext, File dir) throws IOException {
		if (dir == null) {
			String sdir = ApplicationConfig.getDocumentdir();
			dir = new File(sdir);
		}
		if (!dir.exists())
			dir.mkdirs();
		return File.createTempFile(name, ext, dir);

	}

	public static void clean() {
		for (File f : lista) {
			f.delete();
		}
	}
}
