package com.example.demo;

import com.example.demo.model.Account;
import com.example.demo.model.AccountFactory;
import com.example.demo.model.ApiResponse;
import com.example.demo.model.ApiResponseFactory;
import com.example.demo.service.AccountServiceImpl;
import com.example.demo.validation.AccountValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountServiceImpl accountService;

    @Mock
    private AccountValidator accountValidator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void testCreateAccount() {
        // Provide required arguments for the Account constructor
        // In your test
        Account account = AccountFactory.createAccount("John Doe", "Savings", new BigDecimal("1000.00"), "Active");

//        Account account = new Account("John Doe", "Savings", new BigDecimal("1000.00"), "Active");

// In your test code
        ApiResponse mockResponse = ApiResponseFactory.createApiResponse(201, null, "Account created");
        when(accountService.addAccount(account)).thenReturn(mockResponse);

        ResponseEntity<ApiResponse> response = accountController.createAccount(account);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Account created", response.getBody().getMessage());
        verify(accountService, times(1)).addAccount(account);
    }

    @Test
    void testGetAccount() {
        String accountId = "123";
        when(accountValidator.accountExists(accountId)).thenReturn(null);
        // Provide required arguments for Account in ApiResponse
//        Account account = new Account("John Doe", "Savings", new BigDecimal("1000.00"), "Active");
        Account account = AccountFactory.createAccount("John Doe", "Savings", new BigDecimal("1000.00"), "Active");
        ApiResponse mockResponse = ApiResponseFactory.createApiResponse(200, account, "Account details");
//        ApiResponse mockResponse = new ApiResponse(200, account, "Account details");
        when(accountService.getAccount(accountId)).thenReturn(mockResponse);

        ResponseEntity<ApiResponse> response = accountController.getAccount(accountId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Account details", response.getBody().getMessage());
        verify(accountValidator, times(1)).accountExists(accountId);
        verify(accountService, times(1)).getAccount(accountId);
    }

//    @Test
//    void testUpdateAccount() {
//        String accountId = "123";
//        Account account = new Account(); // Replace with actual fields
//        when(accountValidator.accountExists(accountId)).thenReturn(null);
//        ApiResponse mockResponse = new ApiResponse(200, "Account updated");
//        when(accountService.updateAccount(accountId, account)).thenReturn(mockResponse);
//
//        ResponseEntity<ApiResponse> response = accountController.updateAccount(accountId, account);
//
//        assertEquals(200, response.getStatusCodeValue());
//        assertEquals("Account updated", response.getBody().getMessage());
//        verify(accountValidator, times(1)).accountExists(accountId);
//        verify(accountService, times(1)).updateAccount(accountId, account);
//    }
//
//    @Test
//    void testDeleteAccount() {
//        String accountId = "123";
//        when(accountValidator.accountExists(accountId)).thenReturn(null);
//        ApiResponse mockResponse = new ApiResponse(204, "Account deleted");
//        when(accountService.deleteAccount(accountId)).thenReturn(mockResponse);
//
//        ResponseEntity<ApiResponse> response = accountController.deleteAccount(accountId);
//
//        assertEquals(204, response.getStatusCodeValue());
//        verify(accountValidator, times(1)).accountExists(accountId);
//        verify(accountService, times(1)).deleteAccount(accountId);
//    }
//
//    @Test
//    void testGetAllAccounts() {
//        ApiResponse mockResponse = new ApiResponse(200, "All accounts fetched");
//        when(accountService.getAllAccounts()).thenReturn(mockResponse);
//
//        ResponseEntity<ApiResponse> response = accountController.getAllAccounts();
//
//        assertEquals(200, response.getStatusCodeValue());
//        assertEquals("All accounts fetched", response.getBody().getMessage());
//        verify(accountService, times(1)).getAllAccounts();
//    }
}
