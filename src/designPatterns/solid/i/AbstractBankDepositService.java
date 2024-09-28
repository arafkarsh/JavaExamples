/**
 * (C) Copyright 2024 Araf Karsh Hamid
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package designPatterns.solid.i;

/**
 * SOLID Examples
 *
 * Interface segregation principle (ISP)
 * No client should be forced to depend on methods it does not use.
 *
 * This means all the sub-classes of Bank Account Legacy Interface must implement the
 * following interfaces
 * 1. deposit()
 * 2. withdrawal()
 * 3. calculateInterest()
 * 4. applyLoan()
 * 5. emiPayment()
 * 6. getLoanBalance()
 * 7. closeLoan()
 *
 * The issue with above 3 methods available on Interface.
 * - All the implementing classes must have an implementation for all the 3 methods.
 *
 * Model with Issues
 *
 *                                              |--------------------------------|
 *                                             |   BankAccountLegacyService     |
 *                                            |     - deposit()                      |
 *                                           |     - withdrawal()                 |
 *                                          |     - calculateInterest()         |
 *                                         |      - applyLoan()                 |
 *                                        |      - emiPayment()              |
 *                                       |      - getLoanBalance()          |
 *                                      |      - closeLoan()                 |
 *                                    |--------------+----------------|
 *                                                       |
 *                         +----------------------+----------------------+
 *                         |                            |                           |
 *             |--------+-----------|    |-------+---------|   |--------+---------|
 *            |  Loan...Service      |    | Savings...Service |   | Checking...Service |
 *           |-------------------|    |------------------|   |-------------------|
 *
 *   For the accounts mentioned above, withdrawals are applicable only to Savings and Checking
 *   accounts, while Loan accounts do not support withdrawals. Similarly, all four loan-related
 *   methods are irrelevant to Savings and Checking accounts.
 *
 *   1. Savings Account
 *   2. Checking Account
 *   3. Loan Account
 *
 *  Solution
 *  The interface is split into three and the implementing classes can use the appropriate methods.
 *  1. Loan Account Service implements only Loan Account Interface which contains only the
 *     following methods.
 *     - applyLoan()
 *     - emiPayment()
 *     - getLoanBalance()
 *     - closeLoan()
 *   2. Savings and Checking Account Service implements two interfaces
 *      - BankAccountWithdrawalInterface
 *         - withdraw()
 *         - calculateInterest()
 *      - BankAccountDepositInterface
 *         - deposit()
 *                                                                 |--------------------------------------|
 *                                                                 |  BankAccountWithdrawalInterface    |
 *                                                                |              - withdrawal()               |
 *                                                               |             - calculateInterest()        |
 *                                                              |-------------------+-----------------|
 *             |---------------------------|                                      |
 *            |  LoanAccountInterface     |                                       |
 *           |     - applyLoan()             |                                       |
 *          |     - emiPayment()           |                 | ----------------+------------------|
 *         |      - getLoanBalance()      |                 |   BankAccountDepositInterface      |
 *         |     - closeLoan()              |                |            - deposit()                    |
 *        |------------+------+-------|                 |----------------+------------------|
 *                        |                                                          |
 *                       |                                           +-----------+--------+
 *                      |                                           |                          |
 *           |--------+--------|                 |----------+------|    |----------+--------|
 *          |  Loan...Service   |                 | Savings...Service |    | Checking...Service |
 *         |-----------------|                 |-----------------|    |-------------------|
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public abstract class AbstractBankDepositService extends AbstractBankWithdrawalService {


    /**
     * Bank Account Details Service
     *
     * @param _accountBalance
     * @param _accountNumber
     * @param _accountType
     */
    public AbstractBankDepositService(double _accountBalance, String _accountNumber, String _accountType) {
        super(_accountBalance, _accountNumber, _accountType);
    }

    /**
     * Deposit Amount
     * @param _depositAmount
     */
    public void deposit(double _depositAmount) {
        // Code to handle Deposit
    }
}
