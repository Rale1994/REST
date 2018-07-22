package org.koushik.javabrains.messenger.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

//stavljamo prazan root path zato sto on treba da se odnosi na metodu u klasi MessageResource
//tj na metodu getCommentResource
@Path("/")
public class CommentResource {

	@GET
	public String test() {
		return "new sub resource";

	}

	@GET
	@Path("/{commentId}")
	//@PathParam("messageId") nam daje putanju od klase MessageResource  tacnije od njne metode 
	//getCommentResource
	public String test2(@PathParam("messageId") long messageID, @PathParam("commentId") long commentID) {

		return "Method to return comment ID: " + commentID + " for message: " + messageID;
	}
}
