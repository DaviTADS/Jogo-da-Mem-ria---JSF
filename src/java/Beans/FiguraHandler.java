package Beans;

import Model.Figura;
import java.util.Collections;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean
@ViewScoped
public class FiguraHandler {

    private Figura figura;
    private String urlfigura1;
    private String urlfigura2;
    private String urlfigura3;
    
    private Integer index1=null;
    private Integer index2=null;
    private Integer index3=null;
    private int contjogada=0;
    private Integer verificaindex1=null;
    private Integer verificaindex2=null;
    
    @PostConstruct
    public void start(){
        
    this.figura = new Figura();
    figura.montarListaUrl();
    figura.montarListaDinamica();
    Collections.shuffle(figura.getUrlListaDinamica());
        
    }
    
    public void novoJogo(){
     
    this.figura = new Figura();
    figura.montarListaUrl();
    figura.montarListaDinamica();
    Collections.shuffle(figura.getUrlListaDinamica());
    setContjogada(0);
        
    }

    public int getContjogada() {
        return contjogada;
    }

    public void setContjogada(int contjogada) {
        this.contjogada = contjogada;
    }
    

    public int getIndex1() {
        return index1;
    }

    public void setIndex1(int index1) {
        this.index1 = index1;
    }

    public int getIndex2() {
        return index2;
    }

    public void setIndex2(int index2) {
        this.index2 = index2;
    }
    
    public int getIndex3() {
        return index3;
    }

    public void setIndex3(int index3) {
        this.index3 = index3;
    }
    
    

    public String getUrlfigura1() {
        return urlfigura1;
    }

    public void setUrlfigura1(String urlfigura1) {
        this.urlfigura1 = urlfigura1;
    }
    
    public String getUrlfigura2() {
        return urlfigura2;
    }

    public void setUrlfigura2(String urlfigura2) {
        this.urlfigura2 = urlfigura2;
    }
    
    public String getUrlfigura3() {
        return urlfigura3;
    }

    public void setUrlfigura3(String urlfigura3) {
        this.urlfigura3 = urlfigura3;
    }
    

    public Figura getFigura() {
        return figura;
    }

    public void setFigura(Figura figura) {
        this.figura = figura;
    }

    public Integer getVerificaindex1() {
        return verificaindex1;
    }

    public void setVerificaindex1(Integer verificaindex1) {
        this.verificaindex1 = verificaindex1;
    }

    public Integer getVerificaindex2() {
        return verificaindex2;
    }

    public void setVerificaindex2(Integer verificaindex2) {
        this.verificaindex2 = verificaindex2;
    }
    
   
    public void imageClick(int i){
        
        
        String urlfinalizado = figura.getUrlLista().get(i);
        
        //condicional para saber se o usuário clicou numa figura já finalizada
        if((!"imagens/finalizado.png".equals(urlfinalizado))){
          

        if(index1 == null){
            setIndex1(i);
        }else if(index2 == null){
            setIndex2(i);
        }else if(index3 == null){
            setIndex3(i);
        }
        
         // verifica se clicou na mesma figura mais de uma vez para verificar por ela mesma,o que não pode acontecer.
        if(!Objects.equals(index1, index2)){
        figura.getUrlLista().set(i, figura.getUrlListaDinamica().get(i));
        
        if(urlfigura1 == null){
           setUrlfigura1(figura.getUrlLista().get(i));
           
        }else if(urlfigura2 == null){
           setUrlfigura2(figura.getUrlLista().get(i)); 
           
        }else if(urlfigura3 == null){
            setUrlfigura3(figura.getUrlLista().get(i)); 
        }
        
        
        if(urlfigura1!=null && urlfigura2!=null && urlfigura3!=null){
        comparaImagem(urlfigura1,urlfigura2,index1,index2);
        }
        
        }else{   // caso tenha clicado, reseta a imagem interrogacao para aquela figura e reinicia as variaveis logicas
          figura.getUrlLista().set(i,"imagens/interrogacao.png"); 
          index1 = null;
          index2 = null;
          index3 = null;
          urlfigura1=null;
          urlfigura2=null;
          urlfigura3=null;
        }
        }
    }
    
    public void comparaImagem(String url1, String url2, int a,int b){
     
     // se as urls forem iguais, seta nas duas a imagem finalizado.png e reinicia as variaveis logicas   
     if(url1.equals(url2)){
        
      figura.getUrlLista().set(a,"imagens/finalizado.png");
      figura.getUrlLista().set(b,"imagens/finalizado.png");
      urlfigura1 = urlfigura3;
      urlfigura3 = null;
      urlfigura2 = null;
      
      index1 = index3;
      index3 = null;
      index2 = null;
      
      //caso não, devolve o valor padrão interrogacao.png e reinicia as variaveis logicas
     }else{
        figura.getUrlLista().set(a,"imagens/interrogacao.png"); 
        figura.getUrlLista().set(b,"imagens/interrogacao.png"); 
        urlfigura1 = urlfigura3;
        urlfigura3 = null;
        urlfigura2 = null;
        
        index1 = index3;
        index3 = null;
        index2 = null;
     }   
     //incrementa o numero de jogadas que só ocorrem quando a presente função é chamada.
       contjogada++; 
    }
    
   
    
    
}
