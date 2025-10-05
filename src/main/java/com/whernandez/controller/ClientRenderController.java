package com.whernandez.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientRenderController {

	@GetMapping(value = "/clients-html", produces = MediaType.TEXT_HTML_VALUE)
	public String getClientAsHtml() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("<html>");
		sBuilder.append("<body>");
		sBuilder.append(" <div><h1>Cliente</h1>");
		sBuilder.append("  <ul>");
		sBuilder.append("   <li>Nombre: William Hernandez</li>");
		sBuilder.append("   <li>Username: Wil</li>");
		sBuilder.append("  </ul>");
		sBuilder.append("  </div>");
		sBuilder.append("</body>");
		sBuilder.append("</html>");
		return sBuilder.toString();
	}
	
	@GetMapping(value = "/clients-xml", produces = MediaType.APPLICATION_XML_VALUE)
	public String getClientAsXml() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("<xml>");
		sBuilder.append(" <cliente>");
		sBuilder.append(" <nombre>Nombre: William Hernandez</nombre>");
		sBuilder.append(" <username>Username: Will </username>");
		sBuilder.append("</cliente>");
		sBuilder.append(" </xml>");
		return sBuilder.toString();
	}
	
}
