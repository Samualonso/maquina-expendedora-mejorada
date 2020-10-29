public class MaquinaExpendedoraMejorada {

    // El precio del billete
    private int precioBillete;
    // La cantidad de dinero que lleva metida el cliente actual
    private int balanceClienteActual;
    // El total de dinero almacenado en la maquina desde su ultimo vaciado
    private int totalDineroAcumulado;
    // El origen del billete
    private String estacionOrigen;
    // El destino del billete
    private String estacionDestino;
    // El numero de billetes vendidos
    private int billetesVendidos;
    // Maquina con premio o sin premio
    private boolean darPremio;
    // Número máximo de billetes
    private int maximoBilletes;

    /**
     * Crea una maquina expendedora de billetes de tren con el 
     * precio del billete y el origen y destino dados. Se asume que el precio
     * del billete que se recibe es mayor que 0.
     */
    public MaquinaExpendedoraMejorada(int precioDelBillete, String origen, String destino, boolean maquinaConPremio, int numeroMaximoDeBilletes) {
        precioBillete = precioDelBillete;
        balanceClienteActual = 0;
        totalDineroAcumulado = 0;
        estacionOrigen = origen;
        estacionDestino = destino;
        darPremio = maquinaConPremio;
        maximoBilletes = numeroMaximoDeBilletes;
    }

    /**
     * Devuelve el precio del billete
     */
    public int getPrecioBillete() {
        return precioBillete;
    }

    /**
     * Devuelve la cantidad de dinero que el cliente actual lleva introducida
     */
    public int getBalanceClienteActual() {
        return balanceClienteActual;
    }

    /**
     * Simula la introduccion de dinero por parte del cliente actual
     */
    public void introducirDinero(int cantidadIntroducida) {
        if (maximoBilletes <= 0) {
            System.out.println("No se puede introducir más dinero porque no quedan más billetes.");
        }
        else {
            if (cantidadIntroducida > 0) {
                balanceClienteActual = balanceClienteActual + cantidadIntroducida;
            }
            else {
                System.out.println(cantidadIntroducida + " no es una cantidad de dinero valida.");
            }   
        }
    }

    /**
     * Imprime un billete para el cliente actual
     */
    public void imprimirBillete() {
        int cantidadDeDineroQueFalta = precioBillete - balanceClienteActual;
        if (maximoBilletes <= 0) {
            System.out.println("No se puede imprimir otro billete porque no quedan más.");
        }
        else {
            if (cantidadDeDineroQueFalta <= 0) {
                billetesVendidos = billetesVendidos + 1;
                int billetesConPremio = billetesVendidos % 3;
                // Simula la impresion de un billete
                System.out.println("##################");
                System.out.println("# Billete de tren:");
                System.out.println("# De " + estacionOrigen + " a " + estacionDestino);
                System.out.println("# " + precioBillete + " euros.");
                if(darPremio == true) {
                    if (billetesConPremio == 0) {
                        System.out.println("¡Enhorabuena, has ganado un descuento del 10% para compras en el comercio que tú quieras!");
                    }
                }
                System.out.println("##################");
                System.out.println();         

                // Actualiza el total de dinero acumulado en la maquina
                totalDineroAcumulado = totalDineroAcumulado + precioBillete;
                // Reduce el balance del cliente actual dejandole seguir utilizando la maquina
                balanceClienteActual = balanceClienteActual - precioBillete;
                // Añade un billete vendido al total
                maximoBilletes = maximoBilletes - 1;
            }
            else {
                System.out.println("Necesitas introducir " + cantidadDeDineroQueFalta + " euros mas!");  
            }   
        }
    }

    /**
     * Cancela la operacion de compra del cliente actual y le
     * devuelve al cliente el dinero que ha introducido hasta el momento
     */
    public int cancelarOperacionYDevolverDinero() {
        int cantidadDeDineroADevolver;
        cantidadDeDineroADevolver = balanceClienteActual;
        balanceClienteActual = 0;
        return cantidadDeDineroADevolver;
    } 

    public int vaciarDineroDeLaMaquina() {
        int totalDineroDevuelto = 0;
        if (balanceClienteActual > 0) {
            totalDineroDevuelto = -1;
            System.out.println("No se puede realizar el vaciado, operación en curso.");
        }
        else {
            totalDineroDevuelto = totalDineroAcumulado;
        }
        totalDineroAcumulado = 0;
        return totalDineroDevuelto;
    }

    public int getNumeroBilletesVendidos() {
        return billetesVendidos;
    }

    public void imprimeNumeroBilletesVendidos() {
        System.out.println("Se han vendido " + billetesVendidos + " billetes en total");
    }
}
