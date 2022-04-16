<template>
        <!-- PERFIL -->
        <div v-if="nomeAba == 'perfil'" id="perfil">
          <div class="mb-3 draggable ui-draggable ui-draggable-handle row">
              <!-- Administrador -->
            <div class="col-sm-2">
              <label class="font-18 mt-3 font-sec">ADMINISTRADOR</label>
              <div class="border ps-3">
                <label class="font-17 mt-3 font-sec  mb-0">Empresa</label>
                <div class="checkbox">
                  <input
                    id="cadastrar1"
                    type="checkbox"
                    checked=""
                    data-bs-original-title=""
                    title=""
                  />
                  <label class="font-17 font-sec" for="cadastrar1"
                    >Cadastrar</label
                  >
                </div>
                <div class="checkbox">
                  <input
                    id="editar1"
                    type="checkbox"
                    checked=""
                    data-bs-original-title=""
                    title=""
                  />
                  <label class="font-17 font-sec" for="editar1">Editar</label>
                </div>
                <div class="checkbox">
                  <input
                    id="notas1"
                    type="checkbox"
                    checked=""
                    data-bs-original-title=""
                    title=""
                  />
                  <label class="font-17 font-sec" for="notas2"
                    >Notas fiscais</label
                  >
                </div>
                <div class="checkbox">
                  <input
                    id="extrato1"
                    type="checkbox"
                    checked=""
                    data-bs-original-title=""
                    title=""
                  />
                  <label class="font-17 font-sec" for="extrato1">Extrato</label>
                </div>
                <label class="font-17 mt-3 font-sec  mb-0">Cliente</label>
              <div class="checkbox">
                <input
                  id="cadcliente1"
                  type="checkbox"
                    checked=""
                  data-bs-original-title=""
                  title=""
                />
                <label class="font-17 font-sec" for="cadcliente1"
                  >Cadastrar</label
                >
              </div>
              <div class="checkbox">
                <input
                  id="editcliente1"
                  type="checkbox"
                    checked=""
                  data-bs-original-title=""
                  title=""
                />
                <label class="font-17 font-sec" for="editcliente1">Editar</label>
              </div>
              <label class="font-17 mt-3 font-sec  mb-0">Faturamento</label>
              <div class="checkbox">
                <input
                  id="novo1"
                  type="checkbox"
                    checked=""
                  data-bs-original-title=""
                  title=""
                />
                <label class="font-17 font-sec" for="novo1">Novo</label>
              </div>
              <div class="checkbox">
                <input
                  id="acomp1"
                  type="checkbox"
                    checked=""
                  data-bs-original-title=""
                  title=""
                />
                <label class="font-17 font-sec" for="acomp1"
                  >Acompanhamento</label
                >
              </div>
              <label class="font-17 mt-3 font-sec  mb-0">Solicitações</label>
              <div class="checkbox">
                <input
                  id="analise1"
                  type="checkbox"
                    checked=""
                  data-bs-original-title=""
                  title=""
                />
                <label class="font-17 font-sec" for="analise1"
                  >Iniciar análise</label
                >
              </div>
              <div class="checkbox">
                <input
                  id="responder1"
                  type="checkbox"
                    checked=""
                  data-bs-original-title=""
                  title=""
                />
                <label class="font-17 font-sec" for="responder1"
                  >Responder</label
                >
              </div>
              </div>
            </div>
              <!-- Financeiro -->
            <div class="col-sm-2">
              <label class="font-18 mt-3 font-sec">FINANCEIRO</label>
              <div class="border ps-3">
                <label class="font-17 mt-3 font-sec  mb-0">Empresa</label>
                <div class="checkbox">
                  <input
                    id="cadastrar2"
                    type="checkbox"
                    data-bs-original-title=""
                    title=""
                  />
                  <label class="font-17 font-sec" for="cadastrar2"
                    >Cadastrar</label
                  >
                </div>
                <div class="checkbox">
                  <input
                    id="editar2"
                    type="checkbox"
                    data-bs-original-title=""
                    title=""
                  />
                  <label class="font-17 font-sec" for="editar2">Editar</label>
                </div>
                <div class="checkbox">
                  <input
                    id="notas2"
                    type="checkbox"
                    data-bs-original-title=""
                    title=""
                  />
                  <label class="font-17 font-sec" for="notas3"
                    >Notas fiscais</label
                  >
                </div>
                <div class="checkbox">
                  <input
                    id="extrato2"
                    type="checkbox"
                    data-bs-original-title=""
                    title=""
                  />
                  <label class="font-17 font-sec" for="extrato2">Extrato</label>
                </div>
                <label class="font-17 mt-3 font-sec  mb-0">Cliente</label>
              <div class="checkbox">
                <input
                  id="cadcliente2"
                  type="checkbox"
                  data-bs-original-title=""
                  title=""
                />
                <label class="font-17 font-sec" for="cadcliente2"
                  >Cadastrar</label
                >
              </div>
              <div class="checkbox">
                <input
                  id="editcliente2"
                  type="checkbox"
                  data-bs-original-title=""
                  title=""
                />
                <label class="font-17 font-sec" for="editcliente2">Editar</label>
              </div>
              <label class="font-17 mt-3 font-sec  mb-0">Faturamento</label>
              <div class="checkbox">
                <input
                  id="novo2"
                  type="checkbox"
                  data-bs-original-title=""
                  title=""
                />
                <label class="font-17 font-sec" for="novo2">Novo</label>
              </div>
              <div class="checkbox">
                <input
                  id="acomp2"
                  type="checkbox"
                  data-bs-original-title=""
                  title=""
                />
                <label class="font-17 font-sec" for="acomp2"
                  >Acompanhamento</label
                >
              </div>
              <label class="font-17 mt-3 font-sec  mb-0">Solicitações</label>
              <div class="checkbox">
                <input
                  id="analise2"
                  type="checkbox"
                  data-bs-original-title=""
                  title=""
                />
                <label class="font-17 font-sec" for="analise2"
                  >Iniciar análise</label
                >
              </div>
              <div class="checkbox">
                <input
                  id="responder2"
                  type="checkbox"
                  data-bs-original-title=""
                  title=""
                />
                <label class="font-17 font-sec" for="responder2"
                  >Responder</label
                >
              </div>
              </div>
            </div>
              <!-- Gestor -->
            <div class="col-sm-2">
              <label class="font-18 mt-3 font-sec">GESTOR</label>
              <div class="border ps-3">
                <label class="font-17 mt-3 font-sec  mb-0">Empresa</label>
                <div class="checkbox">
                  <input
                    id="cadastrar3"
                    type="checkbox"
                    data-bs-original-title=""
                    title=""
                  />
                  <label class="font-17 font-sec" for="cadastrar3"
                    >Cadastrar</label
                  >
                </div>
                <div class="checkbox">
                  <input
                    id="editar3"
                    type="checkbox"
                    data-bs-original-title=""
                    title=""
                  />
                  <label class="font-17 font-sec" for="editar3">Editar</label>
                </div>
                <div class="checkbox">
                  <input
                    id="notas3"
                    type="checkbox"
                    data-bs-original-title=""
                    title=""
                  />
                  <label class="font-17 font-sec" for="notas1"
                    >Notas fiscais</label
                  >
                </div>
                <div class="checkbox">
                  <input
                    id="extrato3"
                    type="checkbox"
                    data-bs-original-title=""
                    title=""
                  />
                  <label class="font-17 font-sec" for="extrato3">Extrato</label>
                </div>
                <label class="font-17 mt-3 font-sec  mb-0">Cliente</label>
              <div class="checkbox">
                <input
                  id="cadcliente3"
                  type="checkbox"
                  data-bs-original-title=""
                  title=""
                />
                <label class="font-17 font-sec" for="cadcliente3"
                  >Cadastrar</label
                >
              </div>
              <div class="checkbox">
                <input
                  id="editcliente3"
                  type="checkbox"
                  data-bs-original-title=""
                  title=""
                />
                <label class="font-17 font-sec" for="editcliente3">Editar</label>
              </div>
              <label class="font-17 mt-3 font-sec  mb-0">Faturamento</label>
              <div class="checkbox">
                <input
                  id="novo3"
                  type="checkbox"
                  data-bs-original-title=""
                  title=""
                />
                <label class="font-17 font-sec" for="novo3">Novo</label>
              </div>
              <div class="checkbox">
                <input
                  id="acomp3"
                  type="checkbox"
                  data-bs-original-title=""
                  title=""
                />
                <label class="font-17 font-sec" for="acomp3"
                  >Acompanhamento</label
                >
              </div>
              <label class="font-17 mt-3 font-sec  mb-0">Solicitações</label>
              <div class="checkbox">
                <input
                  id="analise3"
                  type="checkbox"
                  data-bs-original-title=""
                  title=""
                />
                <label class="font-17 font-sec" for="analise3"
                  >Iniciar análise</label
                >
              </div>
              <div class="checkbox">
                <input
                  id="responder3"
                  type="checkbox"
                  data-bs-original-title=""
                  title=""
                />
                <label class="font-17 font-sec" for="responder3"
                  >Responder</label
                >
              </div>
              </div>
            </div>
          </div>
          <div class="d-flex mt-5 justify-content-center">
            <button
              class="btn btn-padrao me-3"
            >
              Salvar alterações
            </button>
            <button
              v-b-modal.novo-perfil
              class="btn btn-padrao-inverse"
            >
              Adicionar novo perfil
            </button>
          </div>
        </div>
</template>