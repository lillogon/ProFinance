package com.lillogon.profinance.mapper;

import com.lillogon.profinance.dto.account.AccountRequestDTO;
import com.lillogon.profinance.dto.account.AccountResponseDTO;
import com.lillogon.profinance.entity.Account;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountMapper {

    private final InstitutionMapper institutionMapper;
    private final PartyMapper partyMapper;
    private final CategoryMapper categoryMapper;

    public AccountMapper(InstitutionMapper institutionMapper,
                         PartyMapper partyMapper,
                         CategoryMapper categoryMapper) {
        this.institutionMapper = institutionMapper;
        this.partyMapper = partyMapper;
        this.categoryMapper = categoryMapper;
    }

    public AccountResponseDTO toResponse(Account account) {
        return new AccountResponseDTO(
                account.getId(),
                account.getTransactionType(),
                account.getAccountStatus(),
                account.getPaymentMethod(),
                account.getInstallments(),
                account.getTotalAmount(),
                account.getDiscountAmount(),
                account.getAmount(),
                account.getCreatedAt(),
                account.getSettledAt(),
                account.getCanceledAt(),
                account.getUpdatedAt(),
                institutionMapper.toResponse(account.getInstitution()),
                partyMapper.toResponse(account.getParty()),
                account.getCategory() != null
                        ? categoryMapper.toResponse(account.getCategory())
                        : null
        );
    }

    public List<AccountResponseDTO> toResponseList(List<Account> accounts) {
        return accounts.stream()
                .map(this::toResponse)
                .toList();
    }

    public Account toEntity(AccountRequestDTO dto) {
        Account account = new Account();
        account.setTransactionType(dto.transactionType());
        account.setAccountStatus(dto.accountStatus());
        account.setPaymentMethod(dto.paymentMethod());
        account.setInstallments(dto.installments());
        account.setTotalAmount(dto.totalAmount());
        account.setDiscountAmount(dto.discountAmount());
        return account;
    }
}
