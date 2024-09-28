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
package designPatterns.solid.d;

import designPatterns.core.BankNotificationInterface;

/**
 * SOLID Examples
 *
 * Dependency Inversion Principle (DIP):
 * High-level modules should not depend on low-level modules. Both should depend on
 * abstractions (e.g., interfaces), not on concretions (e.g., classes).
 *
 * Handle all Bank Account related Activities
 *
 * The refactored Code has 3 Core Responsibilities and 2 External Responsibilities
 *    1. Withdrawal Only Related Accounts (Fixed Deposit Account)
 *    2. Deposit and Withdrawal Related Accounts (Savings, Checking & SIP Accounts)
 *    3. Loan Accounts (Loan Accounts)
 *
 * DIP ensures that both high-level and low-level modules depend on abstractions.
 *  - Notification implementation is completely abstracted away.
 *  - Notification is loaded based on the Conditions defined in the Property File.
 *  - Check the BankNotificationConfiguration class for conditional loading.
 *
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
public class BankEmailNotificationService implements BankNotificationInterface {

    // This is pseudo code to explain the Dependency Inversion Principle (DIP)
    public void sendNotification(String notification) {
        // Code to Send Notification
    }
}
