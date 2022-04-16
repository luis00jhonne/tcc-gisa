<template>
  <b-modal
    class="modal fade"
    id="concluir-cadastro"
    tabindex="-1"
    role="dialog"
    aria-labelledby=""
    aria-hidden="true"
    hide-header
  >
    <template>
        <div class="modal-header border-0 mb-2 mt-4 d-flex justify-content-center">
      <div>
        <img src="@/assets/images/check.svg" alt="" />
      </div>
    </div>
    <div class="d-flex justify-content-center">
      <h6 class="mb-3 font-18 text-center">Usuário cadastrado!</h6>
    </div>
    <div class="modal-body border-0 pt-0">
      <div class="row offset-1 col-10">
        <p class="text-center font-14">
          Foi enviado um e-mail de contendo a senha de acesso para o e-mail
          cadastrado: jasson@gmail.com
        </p>
      </div>
    </div>
    </template>
    <template #modal-footer="{ ok }">
      <div class="modal-footer border-0 d-flex justify-content-center">
        <button
          @click="ok()"
          data-bs-dismiss="modal"
          class="btn btn-padrao-inverse px-5"
          type="button"
        >
          Fechar
        </button>
      </div>
    </template>
  </b-modal>
</template>