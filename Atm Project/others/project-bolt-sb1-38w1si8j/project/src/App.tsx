import React, { useState } from 'react';
import { CircleSlash, DollarSign, History, PlusCircle, Power, Receipt } from 'lucide-react';
import { NetworkMonitor } from './utils/networkMonitor';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

type Screen = 'pin' | 'menu' | 'balance' | 'withdraw' | 'deposit' | 'processing' | 'error' | 'receipt' | 'network-check' | 'amount-entry';

interface Transaction {
  type: 'withdraw' | 'deposit';
  amount: number;
  timestamp: Date;
}

function App() {
  const [screen, setScreen] = useState<Screen>('pin');
  const [pin, setPin] = useState('');
  const [balance, setBalance] = useState(1000);
  const [error, setError] = useState('');
  const [amount, setAmount] = useState('');
  const [processing, setProcessing] = useState(false);
  const [transactions, setTransactions] = useState<Transaction[]>([]);
  const [currentTransaction, setCurrentTransaction] = useState<Transaction | null>(null);
  const [networkMessage, setNetworkMessage] = useState('');

  const handlePinInput = (num: string) => {
    if (pin.length < 4) {
      setPin(prev => prev + num);
    }
  };

  const handleClear = () => {
    if (screen === 'pin') {
      setPin('');
    } else if (screen === 'withdraw' || screen === 'deposit' || screen === 'amount-entry') {
      setAmount('');
    }
  };

  const handleCancel = () => {
    setScreen('menu');
    setPin('');
    setAmount('');
    setError('');
    setNetworkMessage('');
  };

  const handleEnter = () => {
    if (screen === 'pin' && pin.length === 4) {
      if (pin === '1234') { // Demo PIN
        setScreen('menu');
        setPin('');
      } else {
        setError('Invalid PIN');
        setPin('');
      }
    } else if (screen === 'amount-entry' && amount) {
      handleWithdraw();
    } else if (screen === 'deposit' && amount) {
      handleDeposit();
    }
  };

  const initiateWithdrawal = async () => {
    setScreen('network-check');
    setNetworkMessage('Please wait while we analyze network conditions...');
    
    try {
      const networkStatus = await NetworkMonitor.checkNetworkConditions();
      
      if (!networkStatus.isOk) {
        setError(networkStatus.message);
        setScreen('error');
        toast.error(networkStatus.message);
        return;
      }

      setNetworkMessage('Network conditions are good. You may proceed with your transaction.');
      await new Promise(resolve => setTimeout(resolve, 2000)); // Show success message for 2 seconds
      setScreen('amount-entry');
      
    } catch (err) {
      setError('Network check failed. Please try again.');
      setScreen('error');
      toast.error('Network check failed');
    }
  };

  const handleWithdraw = async () => {
    setProcessing(true);
    setScreen('processing');

    try {
      const withdrawAmount = Number(amount);
      if (withdrawAmount <= balance) {
        setBalance(prev => prev - withdrawAmount);
        const transaction: Transaction = {
          type: 'withdraw',
          amount: withdrawAmount,
          timestamp: new Date()
        };
        setTransactions(prev => [...prev, transaction]);
        setCurrentTransaction(transaction);
        setScreen('receipt');
        toast.success('Withdrawal successful!');
      } else {
        setError('Insufficient funds');
        setScreen('error');
        toast.error('Insufficient funds');
      }
    } catch (err) {
      setError('Transaction failed. Please try again.');
      setScreen('error');
      toast.error('Transaction failed');
    } finally {
      setProcessing(false);
      setAmount('');
    }
  };

  const handleDeposit = async () => {
    setProcessing(true);
    setScreen('processing');
    
    try {
      await new Promise(resolve => setTimeout(resolve, 2000));
      const depositAmount = Number(amount);
      setBalance(prev => prev + depositAmount);
      const transaction: Transaction = {
        type: 'deposit',
        amount: depositAmount,
        timestamp: new Date()
      };
      setTransactions(prev => [...prev, transaction]);
      setCurrentTransaction(transaction);
      setScreen('receipt');
      toast.success('Deposit successful!');
    } catch (err) {
      setError('Deposit failed. Please try again.');
      setScreen('error');
      toast.error('Deposit failed');
    } finally {
      setProcessing(false);
      setAmount('');
    }
  };

  const renderScreen = () => {
    switch (screen) {
      case 'pin':
        return (
          <div className="screen-content">
            <h2 className="text-2xl mb-4">Enter PIN</h2>
            <div className="pin-display mb-4">
              {'•'.repeat(pin.length).padEnd(4, '_')}
            </div>
          </div>
        );

      case 'menu':
        return (
          <div className="screen-content">
            <h2 className="text-xl mb-4">Select Transaction</h2>
            <div className="grid grid-cols-2 gap-4">
              <button onClick={() => setScreen('balance')} className="menu-btn">
                <DollarSign className="w-6 h-6 mb-2" />
                Balance
              </button>
              <button onClick={() => initiateWithdrawal()} className="menu-btn">
                <CircleSlash className="w-6 h-6 mb-2" />
                Withdraw
              </button>
              <button onClick={() => setScreen('deposit')} className="menu-btn">
                <PlusCircle className="w-6 h-6 mb-2" />
                Deposit
              </button>
              <button onClick={() => setScreen('pin')} className="menu-btn">
                <Power className="w-6 h-6 mb-2" />
                Exit
              </button>
            </div>
          </div>
        );

      case 'network-check':
        return (
          <div className="screen-content">
            <h2 className="text-xl mb-4">Network Analysis</h2>
            <div className="loading-spinner mb-4"></div>
            <p className="text-center text-green-500">{networkMessage}</p>
          </div>
        );

      case 'amount-entry':
        return (
          <div className="screen-content">
            <h2 className="text-xl mb-4">Enter Withdrawal Amount</h2>
            <div className="mb-4">
              <input
                type="number"
                value={amount}
                onChange={(e) => setAmount(e.target.value)}
                className="w-full p-2 text-2xl bg-black border border-green-500 text-green-500"
                placeholder="Enter amount"
              />
            </div>
            <p className="text-sm mb-4">Press ENTER to confirm or CANCEL to go back</p>
          </div>
        );

      case 'balance':
        return (
          <div className="screen-content">
            <h2 className="text-xl mb-4">Current Balance</h2>
            <p className="text-3xl mb-4">${balance.toFixed(2)}</p>
            <button onClick={() => setScreen('menu')} className="menu-btn">
              Back to Menu
            </button>
          </div>
        );

      case 'deposit':
        return (
          <div className="screen-content">
            <h2 className="text-xl mb-4">Deposit Amount</h2>
            <div className="mb-4">
              <input
                type="number"
                value={amount}
                onChange={(e) => setAmount(e.target.value)}
                className="w-full p-2 text-2xl bg-black border border-green-500 text-green-500"
                placeholder="Enter amount"
              />
            </div>
            <p className="text-sm mb-4">Press ENTER to confirm or CANCEL to go back</p>
          </div>
        );

      case 'processing':
        return (
          <div className="screen-content">
            <h2 className="text-xl mb-4">Processing</h2>
            <div className="loading-spinner"></div>
          </div>
        );

      case 'error':
        return (
          <div className="screen-content">
            <h2 className="text-xl mb-4 text-red-500">Error</h2>
            <p className="mb-4">{error}</p>
            <button onClick={() => setScreen('menu')} className="menu-btn">
              Back to Menu
            </button>
          </div>
        );

      case 'receipt':
        return (
          <div className="screen-content">
            <h2 className="text-xl mb-4">Transaction Receipt</h2>
            {currentTransaction && (
              <div className="receipt-content mb-4 text-left w-full max-w-xs">
                <div className="border border-green-500 p-4 rounded">
                  <p className="text-center mb-4 font-bold">TRANSACTION DETAILS</p>
                  <p>Date: {currentTransaction.timestamp.toLocaleString()}</p>
                  <p>Type: {currentTransaction.type.toUpperCase()}</p>
                  <p>Amount: ${currentTransaction.amount.toFixed(2)}</p>
                  <p>Balance: ${balance.toFixed(2)}</p>
                  <p className="text-center mt-4 text-sm">Thank you for using our ATM</p>
                </div>
              </div>
            )}
            <button onClick={() => setScreen('menu')} className="menu-btn">
              Back to Menu
            </button>
          </div>
        );

      default:
        return null;
    }
  };

  return (
    <>
      <ToastContainer position="top-right" autoClose={3000} />
      <div className="atm-container">
        <div className="atm-body">
          <div className="atm-screen">
            <div className="screen-inner">
              {renderScreen()}
            </div>
          </div>
          
          <div className="atm-keypad">
            <div className="number-pad">
              {[1, 2, 3, 4, 5, 6, 7, 8, 9, '', 0, ''].map((num, index) => (
                <button
                  key={index}
                  onClick={() => num !== '' && handlePinInput(num.toString())}
                  className={`keypad-btn ${num === '' ? 'invisible' : ''}`}
                >
                  {num}
                </button>
              ))}
            </div>
            <div className="function-keys">
              <button onClick={handleCancel} className="function-btn cancel">CANCEL</button>
              <button onClick={handleClear} className="function-btn clear">CLEAR</button>
              <button onClick={handleEnter} className="function-btn enter">ENTER</button>
            </div>
          </div>

          <div className="atm-slots">
            <div className="card-slot">
              <div className="slot-label">Card Slot</div>
            </div>
            <div className="cash-slot">
              <div className="slot-label">Cash Dispenser</div>
            </div>
            <div className="receipt-slot">
              <div className="slot-label">Receipt</div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default App;