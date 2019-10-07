package restservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import beans.FotoIntervento;
import common.ApplicationConfig;
import common.TempFileFactory;
import common.TimeUtil;
import database.dao.FotoInterventoDAO;
import managed.Faces;

@Path("/upload")
public class UploadRest {

	@POST
	@Path("/uploadAttachment")
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	public Response uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail, @QueryParam("id") long id) {

		String uploadDir = Faces.HOMEDIR + "resources" + File.separator + "images" + File.separator;


		String filename = writeToFile(uploadedInputStream, uploadDir, ".jpg");

		FotoIntervento foto = new FotoIntervento();
		foto.setFilename(filename);
		foto.setInterventoId(id);
		foto.setTimestamp(TimeUtil.getTimestamp());

		FotoInterventoDAO dao = new FotoInterventoDAO();
		dao.insert(foto);
		String output = "File saved to : " + filename;

		return Response.status(200).entity(output).build();

	}


	private String writeToFile(InputStream uploadedInputStream, String dir, String ext) { // .jpg
		String filename = null;
		try {
			File outFile = TempFileFactory.createTempFile("foto", ext, new File(dir));
			filename = outFile.getName();
			int read = 0;
			byte[] bytes = new byte[1024];
long sum=0;
			try (OutputStream out = new FileOutputStream(outFile);) {
				while ((read = uploadedInputStream.read(bytes)) != -1) {
					out.write(bytes, 0, read);
					sum +=read;
				}
				out.flush();
			}
			saveToBackup(outFile, filename);

			

		} catch (Exception e) {

			e.printStackTrace();
		}
		return filename;
	}

	private void saveToBackup(File outFile, String filename) throws Exception {
		int read = 0;
		byte[] bytes = new byte[1024];
		String backupDir = ApplicationConfig.getDocumentdir();

		if (!backupDir.endsWith(File.separator))
			backupDir += File.separator;
		
		String dir=backupDir+"images";
		File fDir=new File(dir);
		if( !fDir.exists())fDir.mkdirs();
		
		backupDir += "images"+File.separator+filename;

		try (FileInputStream is = new FileInputStream(outFile);) {
			try (OutputStream out = new FileOutputStream(backupDir);) {
				while ((read = is.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				out.flush();
			}
		}
		
	}
}