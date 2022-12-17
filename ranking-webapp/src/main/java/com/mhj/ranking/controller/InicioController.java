package com.mhj.ranking.controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope (value = "session")
@Component (value = "inicio")
@ELBeanName(value = "inicio")
@Join(path = "/ranking", to = "/index.jsf")
public class InicioController {

}
