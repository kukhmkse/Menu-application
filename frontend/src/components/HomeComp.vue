<script setup>

</script>

<template>
  <div class="d-flex justify-center align-center ga-5">
    <router-link :to="{ name: 'Admin' }">
      <button class="pushable">
        <span class="shadow" />
        <span class="edge-1" />
        <span class="front-1"> Admin </span>
      </button>
    </router-link>
    <router-link :to="{ name: 'User' }">
      <button class="pushable">
        <span class="shadow" />
        <span class="edge" />
        <span class="front"> User </span>
      </button>
    </router-link>
  </div>
</template>

<style scoped>
.pushable {
  position: relative;
  background: transparent;
  padding: 0px;
  border: none;
  cursor: pointer;
  outline-offset: 4px;
  outline-color: deeppink;
  transition: filter 250ms;
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
}

.shadow {
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  width: 100%;
  background: hsl(226, 25%, 69%);
  border-radius: 8px;
  filter: blur(2px);
  will-change: transform;
  transform: translateY(2px);
  transition: transform 600ms cubic-bezier(0.3, 0.7, 0.4, 1);
}

.edge {
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  width: 100%;
  border-radius: 8px;
  background: linear-gradient(
      to right,
      hsl(295, 18%, 62%) 0%,
      hsl(295, 26%, 74%) 8%,
      hsl(295, 18%, 62%) 92%,
      hsl(296, 35%, 55%) 100%
  );
}

.front {
  display: block;
  position: relative;
  border-radius: 8px;
  background: hsl(296, 35%, 55%);
  padding: 16px 32px;
  color: white;
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 1.5px;
  font-size: 1rem;
  transform: translateY(-4px);
  transition: transform 600ms cubic-bezier(0.3, 0.7, 0.4, 1);
}

.edge-1 {
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  width: 100%;
  border-radius: 8px;
  background: linear-gradient(
      to right,
      hsl(38, 67%, 56%) 0%,
      hsl(39, 62%, 71%) 8%,
      hsl(38, 67%, 56%) 92%,
      hsl(40, 63%, 49%) 100%
  );
}

.front-1 {
  display: block;
  position: relative;
  border-radius: 8px;
  background: hsl(40, 63%, 49%);
  padding: 16px 32px;
  color: white;
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 1.5px;
  font-size: 1rem;
  transform: translateY(-4px);
  transition: transform 600ms cubic-bezier(0.3, 0.7, 0.4, 1);
}

.pushable:hover {
  filter: brightness(110%);
}

.pushable:hover .front {
  transform: translateY(-6px);
  transition: transform 250ms cubic-bezier(0.3, 0.7, 0.4, 1.5);
}

.pushable:active .front {
  transform: translateY(-2px);
  transition: transform 34ms;
}

.pushable:hover .front-1 {
  transform: translateY(-6px);
  transition: transform 250ms cubic-bezier(0.3, 0.7, 0.4, 1.5);
}

.pushable:active .front-1 {
  transform: translateY(-2px);
  transition: transform 34ms;
}
.pushable:hover .shadow {
  transform: translateY(4px);
  transition: transform 250ms cubic-bezier(0.3, 0.7, 0.4, 1.5);
}

.pushable:active .shadow {
  transform: translateY(1px);
  transition: transform 34ms;
}

.pushable:focus:not(:focus-visible) {
  outline: none;
}
</style>