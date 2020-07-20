package tads.eaj.aula;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ProdutosController {

    @Autowired
    private ProdutoService produtoService;

    @RequestMapping("/")
    public String getHome(Model model, HttpServletRequest request , HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        Cookie cookie = new Cookie("Time", "index");
        Date acessoData = new Date(session.getLastAccessedTime());
        DateFormat format_date = new SimpleDateFormat("EEE-dd-MM-yyyy__hh:mm:ss");
        cookie.setValue(format_date.format(acessoData));
        response.addCookie(cookie);
        model.addAttribute("cookie", cookie.getValue());
        List<Produtos> produtoList = produtoService.findAll();
        model.addAttribute("produtoList", produtoList);
        return "index";
    }

    @RequestMapping("/cadastrar")
    public String getPageCadastro(Model model){
        var produto = new Produtos();
        model.addAttribute("produto", produto);
        return "cadastrar";
    }

    @RequestMapping(value = "/salvar/{flag}", method = RequestMethod.POST)
    public String addProduto(@PathVariable(name = "flag") String flag, @ModelAttribute(name = "produto") @Valid Produtos produto, Errors errors){

        if (errors.hasErrors()){
            if(flag.equalsIgnoreCase("editar")){
                return "editar";
            } else {
                return "cadastrar";
            }
        } else {
            produtoService.add(produto);
            return "redirect:/";
        }
    }

    @RequestMapping("/editar/{id}")
    public ModelAndView editProduto(@PathVariable(name = "id") Long id){
                var modelAndView = new ModelAndView("editar");
                var produto = produtoService.get(id);
                modelAndView.addObject("produto", produto);
                return modelAndView;
    }

    @RequestMapping("/deletar/{id}")
    public String deleteProduto(@PathVariable(name = "id") Long id){
        produtoService.delete(id);
        return "redirect:/";
    }

}
