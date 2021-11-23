using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Inventory : MonoBehaviour
{
    public static int charge = 0;
    public AudioClip collectSound;

    //HUD
    public Texture2D[] hudCharge;
    public RawImage chargeHudGUI;
    public Text textHints;

    // Generator
    public Texture2D[] meterCharge;
    public Renderer meter;

    // Zapałki
    bool haveMatches = false;
    public RawImage matchHudGUI;
    bool campfireIsFire = false;


    // Start is called before the first frame update
    void Start()
    {
        charge = 0;
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    void CellPickup(){
        HUDon();
        AudioSource.PlayClipAtPoint(collectSound, transform.position);
        charge++;
        chargeHudGUI.texture = hudCharge[charge];
        meter.material.mainTexture = meterCharge[charge];
    }

    void HUDon(){
        if(!chargeHudGUI.enabled){
        chargeHudGUI.enabled = true;
        }
    }

    void MatchPickup(){
        haveMatches = true;
        AudioSource.PlayClipAtPoint(collectSound, transform.position);
        matchHudGUI.enabled = true;
    }

    void OnControllerColliderHit(ControllerColliderHit col){
        if(haveMatches && col.gameObject.name == "campfire") {
            LightFire(col.gameObject);
        } else if (!campfireIsFire && col.gameObject.name == "campfire") {
            textHints.SendMessage("ShowHint", "Mógłbym rozpalić ognisko do wezwania pomocy. \nTylko czym...");
        }
    }

    void LightFire(GameObject campfire){
        if(haveMatches){
            ParticleSystem[] fireEmitters;
            fireEmitters = campfire.GetComponentsInChildren<ParticleSystem>();
            foreach(ParticleSystem emitter in fireEmitters){
                emitter.Play();
            }
            campfire.GetComponent<AudioSource>().Play();

            matchHudGUI.enabled=false;
            haveMatches=false;
            campfireIsFire=true;
        }
    }
}
