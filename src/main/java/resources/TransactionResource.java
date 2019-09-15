package resources;

import java.math.BigDecimal;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import dao.entity.Transaction;
import service.transactions.TransactionService;

@Path("/transactions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransactionResource {

	private TransactionService transactionService;

	public TransactionResource(TransactionService transactionService) {
		super();
		this.transactionService = transactionService;
	}
	
	@GET
	public Response FetchAllTransactions() {
		
		List<Transaction> transactions = transactionService.getAllTransactions();
		
		if(transactions != null) {
			return Response
					.status(Status.OK)
					.entity(transactions)
					.build();
		}
		
		throw new WebApplicationException(Status.NOT_FOUND);
	}
	
	@GET
	@Path("/{id}")
	public Response FetchTransactionById(@PathParam("id") String transaction_Id) {
		final Transaction transaction = transactionService.getTransactionByOrderId(transaction_Id);
		if(transaction != null) {
			return Response
					.status(Status.OK)
					.entity(transaction)
					.build();
					
		}
		throw new WebApplicationException(Status.NOT_FOUND);
	}
	
	@POST
	public Response SaveTransaction(Transaction transaction) {
		
		if(transactionService.getTransactionByOrderId(transaction.getId()) != null ) {
			throw new WebApplicationException(Status.BAD_REQUEST);
		}
		
		else {
			transactionService.saveTransaction(transaction.getId(), transaction.getTransaction_date(), transaction.getTotal(), transaction.getCustomer_ID());
			Transaction transactionJSON = transactionService.getTransactionByOrderId(transaction.getId());
			
			if(transactionJSON != null) {
				return Response
						.status(Status.OK)
						.entity(transactionJSON)
						.build();
			}
			
			else {
				throw new WebApplicationException(Status.EXPECTATION_FAILED);
				}
			}
	}
	
	@PUT
	@Path("/{id}")
	public Response updateTransaction(@PathParam("id") String transaction_id, @PathParam("newTotal") BigDecimal total) {
		
		final Transaction transaction = transactionService.getTransactionByOrderId(transaction_id);

		if(transactionService.getTransactionByOrderId(transaction.getId()) == null ) {
			throw new WebApplicationException(Status.BAD_REQUEST);
		}
		else {
			transactionService.updateTransaction(total, transaction_id);
			Transaction updatedTransaction = transactionService.getTransactionByOrderId(transaction_id);
			
			return Response
					.status(Status.OK)
					.entity(updatedTransaction)
					.build();
		}
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteTransaction(@PathParam("id") String transaction_Id) {
		final Transaction transaction = transactionService.getTransactionByOrderId(transaction_Id);
		
		if(transaction == null ) {
			throw new WebApplicationException(Status.BAD_REQUEST);
			}
		
		else {
			int countDeleted = transactionService.deleteTransaction(transaction_Id);
			
			if(countDeleted == 1) {
				return Response
						.status(Status.OK)
						.entity(transaction)
						.build();
			}
			throw new WebApplicationException(Status.EXPECTATION_FAILED);
		}
	}
	
		
}
