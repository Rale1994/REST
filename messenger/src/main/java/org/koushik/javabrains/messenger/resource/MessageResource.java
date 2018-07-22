package org.koushik.javabrains.messenger.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.koushik.javabrains.messenger.model.Message;
import org.koushik.javabrains.messenger.resource.beans.MessageFilterBean;
import org.koushik.javabrains.messenger.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
// ovede vraca vrednosti u JSON formatu
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

	MessageService messageService = new MessageService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Message> getMessages(@BeanParam MessageFilterBean filterBean) {
		if (filterBean.getYear() > 0) {
			return messageService.getAllMessagesForYear(filterBean.getYear());
		}
		if (filterBean.getStart() >= 0 && filterBean.getSize() > 0) {
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessages();

	}

	@POST
	// on ovde prima vrednosti u JSON foramtu
	@Consumes(MediaType.APPLICATION_JSON)
	// ovede vraca vrednosti u JSON formatu
	@Produces(MediaType.APPLICATION_JSON)

	public Response addMassage(Message message, @Context UriInfo uriInfo) throws URISyntaxException {
		Message newMessage = messageService.addMesage(message);
		String newId = String.valueOf(newMessage.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri).entity(newMessage).build();
		// return messageService.addMesage(message);

	}

	@PUT
	@Path("/{messagesId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messagesId") long messagesId, Message message) {
		message.setId(messagesId);
		return messageService.updateMessage(message);
	}

	@DELETE
	@Path("/{messagesId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteMessage(@PathParam("messagesId") long messagesId) {
		messageService.removeMessage(messagesId);
	}

	@GET
	@Path("/{messagesId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("messagesId") long messagesId) {

		return messageService.getMessage(messagesId);

	}

	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		// vraca se objekat klase CommentResource koji se ujedno i pravi
		return new CommentResource();

	}

}
