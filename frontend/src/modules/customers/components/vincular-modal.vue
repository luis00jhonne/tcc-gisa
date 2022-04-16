<template>
  <b-modal
    class="modal fade"
    id="vincular"
    tabindex="-1"
    role="dialog"
    aria-labelledby="vincular-detailsLabel"
    aria-hidden="true"
    size="lg"
    hide-footer
    no-stacking
  >
    <template #modal-header="{ close }">
      <div class="modal-header border-0 pb-0 w-100 mb-1">
        <button
          @click="close()"
          class="btn-close"
          type="button"
          data-bs-dismiss="modal"
          aria-label="Close"
        ></button>
      </div>
    </template>
    <div class="modal-body form-cadastro border-0 pt-0">
       <div class="card rounded mb-3">
        <div class="card-body p-4">
            <div class="row">
                <div class="col-md-12 title-top">Vincular Empresa</div>
                <div class="title-form-step mt-4 mb-4 font-16"><strong>Selecione a empresa que deseja vincular</strong></div>
                <div class="col-12 xl-100 chart_data_left box-col-12 mb-3">
                    <div class="box box-filter">
                        <!--Busca #novo -->
                        <span class="form-filter ml-2">
                            <span class="form">
                                <svg width="16" height="16" viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg"><path d="M10.9167 9.66667H10.2583L10.025 9.44167C10.8417 8.49167 11.3333 7.25833 11.3333 5.91667C11.3333 2.925 8.90833 0.5 5.91667 0.5C2.925 0.5 0.5 2.925 0.5 5.91667C0.5 8.90833 2.925 11.3333 5.91667 11.3333C7.25833 11.3333 8.49167 10.8417 9.44167 10.025L9.66667 10.2583V10.9167L13.8333 15.075L15.075 13.8333L10.9167 9.66667ZM5.91667 9.66667C3.84167 9.66667 2.16667 7.99167 2.16667 5.91667C2.16667 3.84167 3.84167 2.16667 5.91667 2.16667C7.99167 2.16667 9.66667 3.84167 9.66667 5.91667C9.66667 7.99167 7.99167 9.66667 5.91667 9.66667Z" fill="#C2CFE0"></path></svg>
                                <input type="search" placeholder="Busque por Nome, Documento, CNPJ" aria-label="Search" class="">
                            </span>
                        </span>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="row box-cinza">
                            <div class="col-md-12">
                                <div class="title-form-step mt-2 mb-2 font-16"><strong>Empresa Selecionada</strong></div>
                            </div>
                            <div class="col-md-4 mt-2">
                                <strong>CNPJ:</strong>
                                <div class="mt-1">
                                11.222.333/0001-44
                                </div>  
                            </div>
                            <div class="col-md-4 mt-2">
                                <strong>Razão Social:</strong>
                                <div class="mt-1">
                                Nome da Razão Social do Tomador 
                                </div>
                            </div>
                            <div class="col-md-4 mt-2">
                                <strong>Nome Fantasia:</strong>
                                <div class="mt-2">
                                  Nome da Razão Social do Tomador 
                                </div>
                            </div>
                            <div class="col-md-12 mt-2">
                                <strong>Sócios</strong>
                                <div class="mt-2">
                                João da Silva Soares, Felipe de Alcâtra Bertrão
                                </div>
                            </div>
                        </div>
                        <div class="row form-cadastro mt-4">
                             <div class="col-md-12m mt-4 mb-3">
                                <div class="title-form-step mt-2 mb-2 font-16"><strong>Adicionar contato específico </strong> (opcional)</div>
                            </div>
                            <div class="col-12 mb-3">
                                 <label for="">Nome do Contato:</label>
                                 <input id="input-text-1" type="text" data-bs-original-title="" title="" class="form-control">
                            </div>
                            <div class="col-6 mb-3">
                                 <label for="">E-mail:</label>
                                 <input id="input-text-1" type="text" data-bs-original-title="" title="" class="form-control">
                            </div>
                            <div class="col-3 mb-3">
                                 <label for="">Telefone:</label>
                                 <input id="input-text-1" type="text" data-bs-original-title="" title="" class="form-control">
                            </div>
                            <div class="col-3 mb-3">
                                 <label for="">Celular:</label>
                                 <input id="input-text-1" type="text" data-bs-original-title="" title="" class="form-control">
                            </div>
                        </div>
                        <div class="col-12 mt-3 mb-3"><hr></div>
                        <div class="d-flex justify-content-between">
                            <button  
                                @click="close()"
                                type="button"
                                data-bs-dismiss="modal"
                                aria-label="Close"  
                                class="btn btn-padrao-inverse font-14 mt-3">
                                Voltar
                            </button>
                            <button type="submit" class="btn btn-padrao font-14 mt-3">
                               Salvar
                            </button>
                        </div>
                    </div>
                </div>
                
            </div>
        </div>
       </div> 
    </div>
    
    
  </b-modal>
</template>