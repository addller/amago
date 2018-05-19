/* global TAG, getSource, byId, getMousePosition, retangulo, mousePosition, ajaxSendAsJson */
window.onload = function () {
    configureDysplay();
};

let isLogado = {"action": "read", "status": "informational"};

function reTrigger() {
    ajaxSendAsJson("acesso", isLogado).onreadystatechange = function () {
        if (ajaxSucces(this)) {
            let resposta = ajaxParseJson(this);
            if (resposta.mensagem === "true") {
                alert("Redirecionando para: " + resposta.dados);
                triggerRedirect(resposta.dados);
            } else {
                showBody();
            }
        }
    };
}

function enviarCadastro(dadosCadastro) {
    ajaxSendAsJson("acesso", dadosCadastro).onreadystatechange = function () {
        if (ajaxSucces(this)) {
            let resposta = ajaxParseJson(this);
            if (resposta.status === "redirect") {
                byId("formCadastro").reset();
                alert(resposta.mensagem);
                redirect(resposta.dados);
            } else {
                alert(this.responseText);
            }
        }
    };
}

function cadastrar() {
    let formCadastro = document.getElementById("formCadastro");
    let dadosCadastro = {
        "action": "create",
        "loginName": value(formCadastro.loginName),
        "email": value(formCadastro.email),
        "pass": value(formCadastro.pass)
    };
    enviarCadastro(dadosCadastro);
}



function sair() {
    let protocolo = {"action": "sair"};
    let ajax = ajaxSendAsJson("acesso", protocolo);
    ajax.onreadystatechange = function () {
        if (ajaxSucces(this)) {
            let resposta = ajaxParseJson(this);
            if (resposta.status === "redirect") {
                redirect(resposta.dados);
            } else {
                redirect("home");
            }
        }
    };
}
let myDysplay;
function configureDysplay() {
    if (!myDysplay) {
        myDysplay = {
            "filtros": myDysplayElement("filtros"),
            "cadastro": myDysplayElement("cadastro")
        };
    }
    monitoreBodyMousePosition();

    function myDysplayElement(id, isShowing) {
        let element = byId(id);
        let enable = isShowing;
        let autoHideDysplay = function (delay, force) {
            delay = delay ? delay : 1500;
            window.setTimeout(function () {
                if (!enable || force) {
                    hide(element);
                    element.onmousemove = null;
                } else {
                    enable = isMouseOver(element);
                    autoHideDysplay(delay, force);
                }
            }
            , delay);
        };
        return  {
            "element": element,

            "hide": function () {
                hide(element);
            },
            "show": function () {
                show(element);
            },
            "isMouseOverElement": function () {
                return isMouseOver(element);
            },
            "monitoreMouse": function () {
                element.onmousemove = function () {
                    enable = isMouseOver(element);
                };
            },
            "isEnable": function () {
                return enable;
            },
            "autoHide": autoHideDysplay
        };
    }
}

function exibirElementos(nameDisplay, delay) {
    configureDysplay();
    let myElement;
    switch (nameDisplay) {
        case "filtros":
            myElement = myDysplay.filtros;
            break;
        case "cadastro":
            myElement = myDysplay.cadastro;
            break;
    }

    if (!myElement.isEnable()) {
        myElement.show();
        myElement.monitoreMouse();
    }
    delay = delay ? delay : 6000;
    myElement.autoHide(delay);
}

function exibirFiltros() {
    exibirElementos("filtros");
}

function exibirCadastro() {
    exibirElementos("cadastro");
}

function fecharFormFiltros() {
    myDysplay.filtros.hide();
}

function fecharFormCadastro() {
    myDysplay.cadastro.hide();
}
