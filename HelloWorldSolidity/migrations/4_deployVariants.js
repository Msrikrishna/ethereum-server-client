var SriTokenFixedBurnable = artifacts.require("SriTokenFixedBurnable");
var SriTokenFixedMintable = artifacts.require("SriTokenFixedMintable");
var SriTokenCapped = artifacts.require("SriTokenCapped");


module.exports = function(deployer) {
    deployer.deploy(SriTokenFixedBurnable);
    deployer.deploy(SriTokenFixedMintable);
    deployer.deploy(SriTokenCapped, 100000 * (10 ** 8));
    // Additional contracts can be deployed here
};