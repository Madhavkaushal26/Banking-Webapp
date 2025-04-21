import React from 'react';
import { BrowserRouter as Router, Routes, Route, useLocation } from 'react-router-dom';
import LoginPage from './Pages/LoginPage';
import './App.css';
import EmployeeHomePage from './Pages/EmployeeHome';
import CustomerHomePage from './Pages/CustomerHome';
import TransactionPage from './Pages/Transaction';
import PaymentPage from './Pages/PaymentPage';
import OffersAvailablePage from './Pages/OffersAvailablePage';
import OffersAvailedPage from './Pages/OffersAvailedPage';
import AddCustomer from './Pages/AddCustomer';
import EmployeeTransactions from './Pages/EmployeeTransaction';
import CustomerKyc from './Pages/CustomerKYC';
import EmployeeCreateAccount from './Pages/EmployeeCreateAccount';
import EmployeeManageOffers from './Pages/EmployeeManageOffer';
import Navbar from './Components/Navbar';
import ProtectedRoute from './Pages/ProtectedRoute';

const Layout = ({ children }) => {
  const location = useLocation();
  const hideNavbarPaths = ["/"];
  const hideNavbar = hideNavbarPaths.includes(location.pathname);

  return (
    <>
      {!hideNavbar && <Navbar />}
      {children}
    </>
  );
};

const App = () => {
  return (
    <Router>
      <Layout>
        <Routes>
          <Route path="/" element={<LoginPage />} />

          {/* 🔐 Employee Protected Routes */}
          <Route path="/employee/home" element={
            <ProtectedRoute allowedRole="employee">
              <EmployeeHomePage />
            </ProtectedRoute>
          } />
          <Route path="/employee/AddCustomer" element={
            <ProtectedRoute allowedRole="employee">
              <AddCustomer />
            </ProtectedRoute>
          } />
          <Route path="/employee/CustomerKYC" element={
            <ProtectedRoute allowedRole="employee">
              <CustomerKyc />
            </ProtectedRoute>
          } />
          <Route path="/employee/Accounts" element={
            <ProtectedRoute allowedRole="employee">
              <EmployeeCreateAccount />
            </ProtectedRoute>
          } />
          <Route path="/employee/transactions" element={
            <ProtectedRoute allowedRole="employee">
              <EmployeeTransactions />
            </ProtectedRoute>
          } />
          <Route path="/employee/Offers" element={
            <ProtectedRoute allowedRole="employee">
              <EmployeeManageOffers />
            </ProtectedRoute>
          } />

          {/* 🔐 Customer Protected Routes */}
          <Route path="/customer/home" element={
            <ProtectedRoute allowedRole="customer">
              <CustomerHomePage />
            </ProtectedRoute>
          } />
          <Route path="/customer/transaction" element={
            <ProtectedRoute allowedRole="customer">
              <TransactionPage />
            </ProtectedRoute>
          } />
          <Route path="/Payment" element={
            <ProtectedRoute allowedRole="customer">
              <PaymentPage />
            </ProtectedRoute>
          } />
          <Route path="/offers" element={
            <ProtectedRoute allowedRole="customer">
              <OffersAvailablePage />
            </ProtectedRoute>
          } />
          <Route path="/offersAvailed" element={
            <ProtectedRoute allowedRole="customer">
              <OffersAvailedPage />
            </ProtectedRoute>
          } />
        </Routes>
      </Layout>
    </Router>
  );
};

export default App;
