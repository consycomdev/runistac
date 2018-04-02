/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.runt.runistac.filtros;

import co.com.runt.autenticacionrunt.RespuestaAutenticacionDTO;
import co.com.runt.clienteautenticacion.ClienteAutenticacion;

/**
 *
 * @author Usuario
 */
public class Prueba {
    
    
    public static void main(String[] args) {
        String cookie="k9RVgvDvit%2BX%2FNbH%2BBCExJRcQ5DGqTTCDGeZN3eAp10I1R8yHJnn8gHllGqHdQ4llbbJfipwi%2BwvqWfWeRi0Tpf0bRE4%2FFGCcdd6rp63mRLCp87TA0rULqNCXA6mhHE673MX4WCETa6NMGgTxsRy%2FIhhW0F4ZW2ecR08IZp1ylxRcxz8p4TBezjkWc4OxAn4Ee71%2B0B%2FKQ3vJDuHEc18akoIG5DfBT9E0jJOHPIGdYwguBWYFjFx4MER6ubB%2F6%2F8cM%2Bsg%2BSU9GoOFfzUggwEa2VqvDDdEscnJVseSO%2FjeoUKLHqMyScJZP1FzXaE7P2CIdEB8chClPLgr2R3Dne%2F1YcH7tww%2F%2FxPii6udDR%2F1o7414Sig07rjwW0aM6yk6US8mdx%2BjKM2bia3HMXPfPXowC8bLNf73RxFebiWMn9xBoulf4uMK4lIMd0tYm2YmI7vJbyxa5MLvjIXiRcTvwpYqb6eg4zKVDgkseykHTwTO%2FukxzXHgpfoazUrmpLd3JOwo3hppoQLWubQnUVedDaOA%3D%3D";
        ClienteAutenticacion clienteAutenticacion=new ClienteAutenticacion();
        RespuestaAutenticacionDTO respuesta = clienteAutenticacion.obtenerLoginCookie(cookie);
        System.out.println("login: "+respuesta.getLogin());
        
    }
}
