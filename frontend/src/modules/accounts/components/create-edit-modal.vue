<template>
  <b-modal
    class="modal fade"
    id="cadastro"
    tabindex="-1"
    role="dialog"
    aria-labelledby="analise-detailsLabel"
    aria-hidden="true"
    size="lg"
    hide-footer
    no-stacking
  >
    <template #modal-header="{ close }">
      <div class="modal-header border-0 pb-0 w-100">
        <h6 class="mb-0">Cadastrar novo usuário</h6>
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
          <input
            class="form-control"
            id="input-text-1"
            type="text"
            data-bs-original-title=""
            title=""
          />
        </div>
        <div class="col-6">
          <label class="font-15 fw-bolder" for="">E-mail</label>
          <input
            class="form-control"
            id="input-text-1"
            type="text"
            data-bs-original-title=""
            title=""
          />
        </div>
      </div>
      <div class="row mt-4">
        <div class="col-6">
          <label class="font-15 fw-bolder" for="">Celular</label>
          <input
            class="form-control"
            id="input-text-1"
            type="text"
            data-bs-original-title=""
            title=""
          />
        </div>
        <div class="col-6">
          <label class="font-15 fw-bolder" for="">Perfil</label>
          <input
            class="form-control"
            id="input-text-1"
            type="text"
            data-bs-original-title=""
            title=""
          />
        </div>
      </div>
    </div>
    <div class="bg-icongrey rounded p-2 mx-3 my-3">
      <p class="mb-0 fw-bold font-15 text-white">
        A senha será enviada para o e-mail após a confirmação do cadastro.
      </p>
    </div>
    <template>
      <div class="modal-footer border-0 d-flex justify-content-center">
        <a
          v-b-modal.concluir-cadastro
          class="btn btn-padrao-inverse"
          type="button"
          data-bs-toggle="modal"
          data-original-title="Concluir cadastro"
          data-bs-target="#concluir-cadastro"
        >
          Confirmar cadastro
        </a>
      </div>
    </template>
  </b-modal>
</template>