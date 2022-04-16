<template>
  <b-modal
    class="modal fade"
    id="novo-perfil"
    tabindex="-1"
    role="dialog"
    aria-labelledby="analise-detailsLabel"
    aria-hidden="true"
    no-stacking
  >
    <template #modal-header="{ close }">
      <div class="modal-header border-0 pb-0 w-100 mb-4">
        <h6 class="mb-0 font-18 fw-bolder">Novo perfil</h6>
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
        <div>
          <label class="font-15 fw-bolder" for="">Nome do perfil</label>
          <input
            class="form-control mb-3"
            id="input-text-1"
            type="text"
            data-bs-original-title=""
            title=""
          />
        </div>
      </div>
    </div>
    <template #modal-footer="{ok}">
      <div class="modal-footer border-0 d-flex justify-content-center">
        <a
            @click ="ok()"
          class="btn btn-padrao-inverse"
        >
          Confirmar cadastro
        </a>
      </div>
    </template>
  </b-modal>
</template>