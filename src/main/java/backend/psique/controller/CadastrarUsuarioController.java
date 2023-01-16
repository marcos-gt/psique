package backend.psique.controller;

import backend.psique.model.usuario.DadosCadastroForm;
import backend.psique.model.usuario.UsuarioRepository;
import backend.psique.model.usuario.UsuarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
public class CadastrarUsuarioController {
    @Autowired
    private UsuarioServico servico;
    @GetMapping("/cadastrarUsuario")
    public ModelAndView cadastrarUsuario(){
        ModelAndView mv = new ModelAndView("cadastrarUsuario");
        return mv;
    }
    @PostMapping("/cadastrarUsuario")
    public ResponseEntity<?> cadastrarUsuarioPost(@RequestBody DadosCadastroForm obj, BindingResult result, RedirectAttributes attributes){
        System.out.println(obj);
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique se os campos obrigatórios foram preenchidos!");
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        return servico.cadastrarForm(obj);
    }
}
