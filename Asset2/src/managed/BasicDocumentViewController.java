package managed;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public class BasicDocumentViewController implements Serializable {
	private File pdf = null;

	private static final long serialVersionUID = 1L;

	private StreamedContent content;

	private String contentUrl;

	public void onPrerender(ComponentSystemEvent event) {

		try {
	//		pdf = new File("c:\\books.pdf");
			contentUrl = pdf.toURI().toURL().toString();
			content = new DefaultStreamedContent(new FileInputStream(pdf), "application/pdf", "p.pdf");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public StreamedContent getContent() {
	//	return content;
		
		  FacesContext context = FacesContext.getCurrentInstance();

	        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
	            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
	            return new DefaultStreamedContent();
	        } else {
	        	try {
	        		System.out.println("file:"+pdf);
					return  new DefaultStreamedContent(new FileInputStream(pdf), "application/pdf", "p.pdf");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            // So, browser is requesting the media. Return a real StreamedContent with the media bytes.
//	            String id = context.getExternalContext().getRequestParameterMap().get("id");
//	            Media media = service.find(Long.valueOf(id));
//	            return new DefaultStreamedContent(new ByteArrayInputStream(media.getBytes()));
	        }
	        return null;
	}

	public void setContent(StreamedContent content) {
		this.content = content;
	}

	public File getPdf() {
		return pdf;
	}

	public void setPdf(File pdf) {
		System.out.println("set file "+pdf);
		this.pdf = pdf;
	}

	public String getContentUrl() {
		return contentUrl;
	}

	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}
	public String generateRandomIdForNotCaching() {
		return java.util.UUID.randomUUID().toString();
}
}