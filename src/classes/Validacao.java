package classes;

import java.util.HashMap;
import java.util.Map;

public class Validacao {
    private Map<String, String> listaCredenciais;

    public Validacao (){
        listaCredenciais = new HashMap<>();
        listaCredenciais.put("anaclara", "Senha@123");
        listaCredenciais.put("admin", "Senha#123");
    }
    public boolean autenciar(String usuario, String senha) {
        if (usuario == null || senha == null) {
            return false;
        }
        String senhaUsuario = listaCredenciais.get(usuario);
        return senha.equals(senhaUsuario);
    }
    public boolean redefinirSenha(String usuario, String novaSenha) {
        if (usuario == null || novaSenha == null) {
            return false;
        }
        if(!validaSenha(novaSenha)) {
            return false;
        }

        // Se o usuario já existe, eu atualizo. Se ele não existe, eu insiro um novo.
        listaCredenciais.put(usuario, novaSenha);
        return true;

    }
    private boolean validaSenha (String senha) {
        if(senha.length() < 6) {
            return false;
        }
        boolean temEspecial = senha.contains("$") || senha.contains("#") || senha.contains("@");
        if(!temEspecial) {
            return false;
        }
        boolean temDigitoNumerico = false;
        for (int i = 0; i < senha.length(); i++) {
            char caracterAtual = senha.charAt(i);
            if (caracterAtual >= '0' && caracterAtual <= '9') {
                temDigitoNumerico = true;
                break;
            }
        }
        return temDigitoNumerico;
    }
}
