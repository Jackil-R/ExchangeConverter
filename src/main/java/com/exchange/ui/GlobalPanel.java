package com.exchange.ui;

import com.exchange.converter.FreeCurrencyConverter;
import com.exchange.model.Country;
import com.exchange.model.Currency;

import javax.swing.*;
import java.awt.*;

import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.List;

public class GlobalPanel extends JPanel {

    private final JButton convertButton;
    private final JButton closeButton;
    private final JButton clearButton;
    private final JLabel fromThisAmountLabel;
    private final JLabel amountLabel;
    private final JLabel errorLabel;
    private final JTextField enteredAmountTextField;
    private final ComboBox fromAmount;
    private final ComboBox toAmount;
    private final JTextField calculatedAmount;
    private final JLabel resultLabel;
    private final JLabel toThisAmountLabel;
    private final JLabel currencyConverterLabel;
    private final JPanel mainFrame;
    private final JPanel titleFrame;
    private final JPanel buttonFrama;
    private final JLabel exchangeConverterLabel;
    private FreeCurrencyConverter freeCurrencyConverter;
    private final List<Country> countries;
    private final List<Currency> currencies;

    public GlobalPanel(){
        freeCurrencyConverter = new FreeCurrencyConverter();
        countries  = loadCountries();
        currencies = loadCurrencies();
        convertButton = new JButton();
        closeButton = new JButton();
        clearButton = new JButton();
        fromThisAmountLabel = new JLabel();
        amountLabel = new JLabel();
        errorLabel = new JLabel();
        enteredAmountTextField = new JTextField();
        fromAmount = new ComboBox<>(getCountryNameWithCurrency(countries));
        toAmount = new ComboBox<>(getCountryNameWithCurrency(countries));
        calculatedAmount = new JTextField();
        resultLabel = new JLabel();
        toThisAmountLabel = new JLabel();
        currencyConverterLabel = new JLabel();
        mainFrame = new JPanel();
        titleFrame = new JPanel();
        buttonFrama = new JPanel();
        exchangeConverterLabel = new JLabel();

        this.setLayout(new BorderLayout(0, 0));
        this.setVisible(true);
        titleFrame.setLayout(new GridBagLayout());
        this.add(titleFrame, BorderLayout.NORTH);
        exchangeConverterLabel.setText("");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(0, 300, 0, 300);
        titleFrame.add(exchangeConverterLabel, gbc);

        // main frame
        mainFrame.setLayout(new GridBagLayout());
        this.add(mainFrame, BorderLayout.CENTER);

        // title
        currencyConverterLabel.setFont(new Font("Arial", Font.BOLD, 26));
        currencyConverterLabel.setForeground(Color.BLUE);
        currencyConverterLabel.setText("Currency Converter");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(50, 0, 40, 50);
        mainFrame.add(currencyConverterLabel, gbc);

        // amount for exchange
        amountLabel.setText("Convert this amount:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 0, 50);
        mainFrame.add(amountLabel, gbc);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        enteredAmountTextField.setSize(150,20);
        enteredAmountTextField.setPreferredSize(enteredAmountTextField.getSize());
        gbc.insets = new Insets(0, 0, 30, 0);
        mainFrame.add(enteredAmountTextField, gbc);


        // From amount
        fromThisAmountLabel.setText("From this amount:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 10, 0, 50);
        mainFrame.add(fromThisAmountLabel, gbc);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 30, 0);
        mainFrame.add(fromAmount, gbc);

        // To amount
        toThisAmountLabel .setText("To this amount:");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 0, 50);
        mainFrame.add(toThisAmountLabel, gbc);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 30, 0);
        mainFrame.add(toAmount, gbc);

        // converted amount
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        resultLabel.setText("Result:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 0, 50, 0);
        gbc.anchor = GridBagConstraints.EAST;
        mainFrame.add(resultLabel, gbc);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 50, 0);
        calculatedAmount.setFont(new Font("Arial", Font.BOLD, 16));
        calculatedAmount.setEditable(false);
        mainFrame.add(calculatedAmount, gbc);
        // error label
        errorLabel.setFont(new Font("Arial", Font.BOLD, 12));
        errorLabel.setForeground(Color.RED);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 0, 50, 0);
        mainFrame.add(errorLabel, gbc);

        // button frame
        buttonFrama.setLayout(new GridBagLayout());
        // convert button
        this.add(buttonFrama, BorderLayout.SOUTH);
        clearButton.setText("Clear");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 50;
        gbc.ipady = 10;
        gbc.insets = new Insets(0, 0, 50, 10);
        buttonFrama.add(clearButton, gbc);

        // convert button
        this.add(buttonFrama, BorderLayout.SOUTH);
        convertButton.setText("Convert");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 50;
        gbc.ipady = 10;
        gbc.insets = new Insets(0, 0, 50, 10);
        buttonFrama.add(convertButton, gbc);

        // close button
        closeButton.setText("Close");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 50;
        gbc.ipady = 10;
        gbc.insets = new Insets(0, 0, 50, 0);
        buttonFrama.add(closeButton, gbc);

        closeButton.addActionListener(e -> System.exit(0));
        convertButton.addActionListener(e -> clickConvert());
        clearButton.addActionListener( e -> resetField());
    }

    public void clickConvert(){
        if(enteredAmountTextField.getText().isEmpty() || enteredAmountTextField.getText().isEmpty()){
            errorLabel.setText("Please enter a amount!");
        }else if(!enteredAmountTextField.getText().matches("^\\d*\\.?\\d*$")) {
            errorLabel.setText("Please enter a valid amount!");
        } else if(String.valueOf(fromAmount.getSelectedItem()).isEmpty() || String.valueOf(fromAmount.getSelectedItem()).isEmpty()){
            errorLabel.setText("Please select the from currency!");
        }else if(String.valueOf(toAmount.getSelectedItem()).isEmpty() || String.valueOf(toAmount.getSelectedItem()).isEmpty()){
            errorLabel.setText("Please select the to currency!");
        }else {
            float amount = Float.parseFloat(enteredAmountTextField.getText());
            String from = String.valueOf(fromAmount.getSelectedItem());
            String currencyFrom = from.substring(from.indexOf('(')+1, from.length() - 1).toUpperCase();
            String to = String.valueOf(toAmount.getSelectedItem());
            String currencyTo = to.substring(to.indexOf('(')+1, to.length() - 1).toUpperCase();
            float rate = convert(currencyFrom, currencyTo);
            float calAmount = (amount*rate);
            calculatedAmount.setText(String.valueOf(round(calAmount,2)));
            errorLabel.setText("");
        }
    }

    public void resetField(){
        enteredAmountTextField.setText("");
        calculatedAmount.setText("");
        errorLabel.setText("");
        fromAmount.setSelectedIndex(0);
        toAmount.setSelectedIndex(0);
    }

    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    private float convert(String currencyFrom, String currencyTo) {
        return freeCurrencyConverter.convert(currencyFrom, currencyTo);
    }

    private String[] getCountryNameWithCurrency(List<Country> countries) {
        return countries.stream()
                .map(c -> c.getName() + " (" + c.getCurrencyId() + ")")
                .sorted()
                .collect(Collectors.toList())
                .toArray(String[]::new);
    }

    private List<Country> loadCountries(){
        return freeCurrencyConverter.getCountryArrayList();
    }

    private List<com.exchange.model.Currency> loadCurrencies(){
        return freeCurrencyConverter.getCurrencyArrayList();
    }



}
