package ma.enset.bankaccountservice.services;

import ma.enset.bankaccountservice.dtos.BankAccountRequestDTO;
import ma.enset.bankaccountservice.dtos.BankAccountResponseDto;

import java.util.List;

public interface AccountService {


    BankAccountResponseDto addAccount(BankAccountRequestDTO bankAccountDTO);

    BankAccountResponseDto updateAccount(String id, BankAccountRequestDTO bankAccountDTO);

    List<BankAccountResponseDto> getAllAccounts();

    BankAccountResponseDto getAccountById(String id);
}
