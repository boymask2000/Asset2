package restservice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import beans.FotoIntervento;
import beans.Intervento;
import common.JsfUtil;
import common.TempFileFactory;
import common.TimeUtil;
import database.dao.FotoInterventoDAO;
import managed.Faces;
import managed.ManagedInterventiBean;

@Path("/upload")
public class UploadRest {

	@POST
	@Path("/uploadAttachment")
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	public Response uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail, @QueryParam("id") long id) {

		String uploadDir = Faces.HOMEDIR + "resources" + File.separator + "images" + File.separator;

		String filename = writeToFile(uploadedInputStream, uploadDir,".jpg");

		FotoIntervento foto = new FotoIntervento();
		foto.setFilename(filename);
		foto.setInterventoId(id);
		foto.setTimestamp(TimeUtil.getTimestamp());

		FotoInterventoDAO dao = new FotoInterventoDAO();
		dao.insert(foto);
		String output = "File saved to : " + filename;

		return Response.status(200).entity(output).build();

	}
	@POST
	@Path("/uploadAudio")
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	public Response uploadAudio(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail, @QueryParam("id") long id) {

		String uploadDir = "/home/giovanni/Desktop" + File.separator;

		String filename = writeToFile(uploadedInputStream, uploadDir,".3gp");
System.out.println(filename);
//		FotoIntervento foto = new FotoIntervento();
//		foto.setFilename(filename);
//		foto.setInterventoId(id);
//		foto.setTimestamp(TimeUtil.getTimestamp());
//
//		FotoInterventoDAO dao = new FotoInterventoDAO();
//		dao.insert(foto);
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

			OutputStream out = new FileOutputStream(outFile);
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return filename;
	}
}