<template>
  <b-modal
    class="modal fade"
    id="detalhe-usuario"
    tabindex="-1"
    role="dialog"
    aria-labelledby="analise-detailsLabel"
    aria-hidden="true"
    size="lg"
    hide-footer
    no-stacking
  >
    <template #modal-header="{ close }">
      <div class="modal-header border-0 pb-0 w-100 mb-4">
        <h6 class="mb-0">Usuário cadastrado</h6>
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
      <div class="row">
        <div class="col-6">
          <label class="font-15 fw-bolder" for="">Nome</label>
          <p class="font-14">Caio de Paiva</p>
        </div>
        <div class="col-6">
          <label class="font-15 fw-bolder" for="">E-mail</label>
          <p class="font-14">caio@gmail.com</p>
        </div>
      </div>
      <div class="row mt-4">
        <div class="col-6">
          <label class="font-15 fw-bolder" for="">Celular</label>
          <p class="font-14">71 99876-6789</p>
        </div>
        <div class="col-6">
          <label class="font-15 fw-bolder" for="">Perfil</label>
          <p class="font-14">Administrador</p>
        </div>
      </div>
    </div>
    
    <template>
      <div class="modal-footer border-0 my-4 d-flex justify-content-center">
        <a
          v-b-modal.cadastro
          class="btn btn-padrao-inverse"
          type="button"
          data-bs-toggle="modal"
          data-original-title="Concluir cadastro"
          data-bs-target="#concluir-cadastro"
        >
          Editar usuário
        </a>
      </div>
    </template>
  </b-modal>
</template>