package com.example.BookTransaction.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.BookTransaction.Model.BookTransaction;
import com.example.BookTransaction.Service.BookTransactionService;

@RestController
public class BookTransactionController {
    
    @Autowired
    BookTransactionService bookTransactionService;

    @PostMapping("/createBookTransaction")
    public BookTransaction creareBookTransaction(@RequestBody BookTransaction bookTrans){
        return bookTransactionService.createBookTransaction(bookTrans);
    }

    @GetMapping("/getBookTransaction/{id}")
    public BookTransaction getTransactionById(@PathVariable("id") String id){
        return bookTransactionService.getTransactionById(id);
    }

    @PostMapping("/listTransaction")
    public List<BookTransaction> listTransaction(
        @RequestParam(required = false) String customerId,
        @RequestParam(required = false) String status,
        @RequestParam(required = false) String originatorAccountId,
        @RequestParam(required = false) String beneficiaryAccountId,
        @RequestParam(defaultValue = "ascending") String order
        ) {
       return bookTransactionService.listTransaction( customerId, status, order, originatorAccountId, beneficiaryAccountId);
    }

}
